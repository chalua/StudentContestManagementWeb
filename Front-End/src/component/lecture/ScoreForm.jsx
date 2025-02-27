import React, { useEffect, useState } from 'react';
import { Button, Select, InputNumber, Input, Form } from 'antd';
import { useForm } from 'antd/es/form/Form';
import useApplyScore from '../../hooks/lecture/useApplyScore';
import { toast } from 'react-toastify';
import { useQueryClient } from '@tanstack/react-query';
import useGetScore from '../../hooks/lecture/useGetScore';

const ScoreForm = ({ allRounds, round, selectedTeam }) => {
  const [form] = useForm();
  const [isDisabled, setIsDisabled] = useState(false); // State to track if button should be disabled

  const applyScore = useApplyScore();
  const { data: score } = useGetScore(selectedTeam?.maNhom, round.maVongThi);
  const queryClient = useQueryClient();

  // Lấy dữ liệu của vòng trước đó
  const { data: previousScore } = useGetScore(
    selectedTeam?.maNhom,
    allRounds.find((r) => r.soThuTuVongThi === round.soThuTuVongThi - 1)
      ?.maVongThi
  );

  // Lấy dữ liệu của vòng 1
  const { data: roundOneScore } = useGetScore(
    selectedTeam?.maNhom,
    allRounds.find((r) => r.soThuTuVongThi === 1)?.maVongThi
  );

  // Kiểm tra trạng thái của vòng trước và vòng 1
  useEffect(() => {
    // Nếu vòng thi trước không đạt hoặc vòng 1 chưa được chấm điểm, disable nút "Chấm điểm"
    if (
      previousScore?.data?.dat === false ||
      (round.soThuTuVongThi > 1 && !roundOneScore?.data?.dat)
    ) {
      setIsDisabled(true);
    } else {
      setIsDisabled(false);
    }
  }, [previousScore, roundOneScore, round.soThuTuVongThi]);

  // Xử lý khi form được submit
  const onFinish = async (values) => {
    const { ketQua, maNhomSinhVien, maVongThi, dat } = values;

    // Nếu vòng thi hiện tại không đạt (dat === false), tự động điền điểm 0 và "Không đạt" cho các vòng sau
    if (dat === false) {
      // Lấy tất cả các vòng thi sau vòng hiện tại (dùng round.soThuTuVongThi để biết vòng nào)
      const futureRounds = allRounds.filter(
        (r) => r.soThuTuVongThi > round.soThuTuVongThi
      );

      console.log('check futureRounds', futureRounds);

      // Cập nhật điểm cho các vòng tiếp theo là 0 và "Không đạt"
      futureRounds.forEach((futureRound) => {
        // Tạo dữ liệu để submit cho các vòng tiếp theo
        applyScore.mutateAsync({
          ketQua: 0,
          maNhomSinhVien,
          maVongThi: futureRound.maVongThi,
          dat: false,
        });
      });
    }

    // Xác nhận điểm cho vòng hiện tại
    await applyScore.mutateAsync({
      ketQua,
      maNhomSinhVien,
      maVongThi,
      dat,
    });

    queryClient.invalidateQueries({ queryKey: ['rounds', round.cuocThiId] });
    queryClient.invalidateQueries();

    toast.success('Chấm điểm thành công');
  };

  const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };

  // Cập nhật form khi có thay đổi
  useEffect(() => {
    form.setFieldsValue({
      stt_vong: round.soThuTuVongThi,
      maVongThi: round.maVongThi,
      maNhomSinhVien: selectedTeam.maNhom,
      ketQua: score?.data?.ketQua ?? undefined,
      dat: score?.data?.dat ?? undefined,
    });
  }, [round, selectedTeam, score]);

  return (
    <Form
      form={form}
      name='basic'
      layout='inline'
      style={{
        marginBottom: 24,
      }}
      initialValues={{
        remember: true,
      }}
      onFinish={onFinish}
      onFinishFailed={onFinishFailed}
      autoComplete='off'
    >
      <Form.Item label='Vòng' name='stt_vong'>
        <Input disabled placeholder='Vòng' value={round.soThuTuVongThi} />
      </Form.Item>

      <Form.Item hidden label='maVongThi' name='maVongThi'>
        <Input placeholder='Mã vòng thi' />
      </Form.Item>

      <Form.Item hidden label='maNhomSinhVien' name='maNhomSinhVien'>
        <Input placeholder='Mã nhóm' />
      </Form.Item>

      <Form.Item
        label='Kết quả'
        name='ketQua'
        rules={[
          {
            required: true,
            message: 'Vui lòng nhập kết quả',
          },
        ]}
      >
        <InputNumber placeholder='Điểm' min={0} max={10} />
      </Form.Item>

      <Form.Item
        label='Được vào vòng kế tiếp?'
        name='dat'
        style={{ width: 280 }}
        rules={[
          {
            required: true,
            message: 'Vui lòng chọn được / không được',
          },
        ]}
      >
        <Select
          options={[
            { label: 'Được', value: true },
            { label: 'Không được', value: false },
          ]}
        />
      </Form.Item>

      <Form.Item style={{ margin: 0 }}>
        <Button
          type='primary'
          htmlType='submit'
          disabled={isDisabled} // Disable the button if the previous round is "Không đạt" or round 1 is not scored
        >
          Chấm điểm
        </Button>
      </Form.Item>
    </Form>
  );
};

export default ScoreForm;

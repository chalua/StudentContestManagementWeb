import { useQueryClient } from '@tanstack/react-query';
import { Button, DatePicker, Form } from 'antd';
import dayjs from 'dayjs';
import React, { useEffect } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import { useParams } from 'react-router-dom';
import { toast } from 'react-toastify';
import useCreateRound from '../../hooks/competition/useCreateRound';
import { formatDateString } from '../../utils/formatDay';
import useUpdateRound from '../../hooks/competition/useUpdateRound';
import useGetRounds from '../../hooks/competition/useGetRounds';

const { RangePicker } = DatePicker;
const formItemLayout = {
  labelCol: { xs: { span: 24 }, sm: { span: 6 } },
  wrapperCol: { xs: { span: 24 }, sm: { span: 14 } },
};

const CompetitionRoundForm = ({ round }) => {
  const { id: competitionId } = useParams();

  const createRound = useCreateRound();
  const updateRound = useUpdateRound();

  const queryClient = useQueryClient();
  const [form] = Form.useForm();

  const onFinish = async (values) => {
    const ngayBatDau = formatDateString(values?.ngayBatDau[0]['$d']);
    const ngayKetThuc = formatDateString(values?.ngayBatDau[1]['$d']);

    const data = {
      ...values,
      ngayBatDau,
      ngayKetThuc,
      cuocThiId: parseInt(competitionId),
    };

    if (round?.maVongThi) {
      await updateRound.mutateAsync({ ...data, maVongThi: round.maVongThi });
      toast.success('Cập nhật vòng thi thành công');
    } else {
      await createRound.mutateAsync(data);
      toast.success('Tạo nội dung cho vòng thi thành công');
    }

    queryClient.invalidateQueries(['rounds', competitionId]);
  };

  useEffect(() => {
    if (round) {
      const formattedStartDate = round.ngayBatDau
        ? dayjs(round.ngayBatDau)
        : null;
      const formattedEndDate = round.ngayKetThuc
        ? dayjs(round.ngayKetThuc)
        : null;

      form.setFieldsValue({
        noiDung: round.noiDung,
        ngayBatDau: [formattedStartDate, formattedEndDate],
      });
    }
  }, [round, form]);

  return (
    <Form
      form={form}
      {...formItemLayout}
      onFinish={onFinish}
      style={{ maxWidth: '100%' }}
      initialValues={{ variant: 'filled' }}
    >
      <Form.Item
        label='Nội dung'
        name='noiDung'
        rules={[{ required: true, message: 'Vui lòng nhập dữ liệu' }]}
      >
        <ReactQuill theme='snow' />
      </Form.Item>

      <Form.Item
        label='Thời gian'
        name='ngayBatDau'
        rules={[{ required: true, message: 'Vui lòng nhập dữ liệu' }]}
      >
        <RangePicker />
      </Form.Item>

      <Form.Item wrapperCol={{ offset: 6, span: 16 }}>
        <Button type='primary' htmlType='submit'>
          Xác nhận
        </Button>
      </Form.Item>
    </Form>
  );
};

export default CompetitionRoundForm;

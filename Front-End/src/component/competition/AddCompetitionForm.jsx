import React, { useEffect, useState } from 'react';
import {
  Button,
  Cascader,
  DatePicker,
  Form,
  Input,
  InputNumber,
  Mentions,
  Select,
  TreeSelect,
  Segmented,
} from 'antd';
import 'react-quill/dist/quill.snow.css';
import ReactQuill from 'react-quill';
import { formatDateString } from '../../utils/formatDay';
import useCreateCompetition from '../../hooks/competition/useCreateCompetition';
import useUpdateCompetition from '../../hooks/competition/useUpdateCompetition';
import { toast } from 'react-toastify';
import { useQueryClient } from '@tanstack/react-query';
import { useLocation } from 'react-router-dom';
import dayjs from 'dayjs';

const { RangePicker } = DatePicker;
const formItemLayout = {
  labelCol: {
    xs: {
      span: 24,
    },
    sm: {
      span: 6,
    },
  },
  wrapperCol: {
    xs: {
      span: 24,
    },
    sm: {
      span: 14,
    },
  },
};
const AddCompetitionForm = ({ selectedCompetition, setOpen }) => {
  const createCompetition = useCreateCompetition();
  const updateCompetition = useUpdateCompetition();

  const queryClient = useQueryClient();

  const [form] = Form.useForm();

  const location = useLocation();

  const isCompetition = location.pathname == '/admin/manage-competition';

  const type = {
    '/admin/manage-competition': 1,
    '/admin/manage-competition-project': 2,
    '/admin/manage-scientific': 3,
  };

  const onFinish = async (values) => {
    const soLuongVongThi = 1;
    const trangThai = 'CHUA_DIEN_RA';
    const maLoaiCuocThi = type[location.pathname];
    const ngayBatDau = formatDateString(values?.ngayBatDau[0]['$d']);
    const ngayKetThuc = formatDateString(values?.ngayBatDau[1]['$d']);

    const data = {
      ...values,
      soLuongVongThi,
      trangThai,
      maLoaiCuocThi,
      ngayBatDau,
      ngayKetThuc,
    };

    if (Object.keys(selectedCompetition).length) {
      await updateCompetition.mutateAsync({
        ...data,
        id: selectedCompetition.id,
      });
      toast.success('Cập nhật cuộc thi thành công');
    } else {
      await createCompetition.mutateAsync(data);
      toast.success('Tạo cuộc thi thành công');
    }

    setOpen(false);

    queryClient.invalidateQueries(['competitionList', maLoaiCuocThi]);
  };

  useEffect(() => {
    if (selectedCompetition) {
      const formattedStartDate = selectedCompetition.ngayBatDau
        ? dayjs(selectedCompetition.ngayBatDau)
        : null;
      const formattedEndDate = selectedCompetition.ngayKetThuc
        ? dayjs(selectedCompetition.ngayKetThuc)
        : null;

      form.setFieldsValue({
        chuDe: selectedCompetition.chuDe,
        tenCuocThi: selectedCompetition.tenCuocThi,
        moTa: selectedCompetition.moTa,
        soLuongNhom: selectedCompetition.soLuongNhom,
        ngayBatDau: [formattedStartDate, formattedEndDate],
      });
    }
  }, [selectedCompetition, form]);

  return (
    <Form
      form={form}
      {...formItemLayout}
      variant={'filled'}
      style={{
        maxWidth: '100%',
      }}
      initialValues={{
        variant: 'filled',
      }}
      onFinish={onFinish}
    >
      <Form.Item
        label='Chủ đề'
        name='chuDe'
        rules={[
          {
            required: true,
            message: 'Vui lòng nhập dữ liệu',
          },
        ]}
      >
        <Input />
      </Form.Item>

      <Form.Item
        label='Tên cuộc thi'
        name='tenCuocThi'
        rules={[
          {
            required: true,
            message: 'Vui lòng nhập dữ liệu',
          },
        ]}
      >
        <Input />
      </Form.Item>

      {/* Drop down loại cuộc thi */}

      <Form.Item
        label='Mô tả'
        name='moTa'
        rules={[
          {
            required: true,
            message: 'Vui lòng nhập dữ liệu',
          },
        ]}
      >
        <ReactQuill
          theme='snow'
          modules={{
            clipboard: {
              matchVisual: false,
            },
          }}
        />
      </Form.Item>

      <Form.Item
        label='Số lượng nhóm tối đa'
        name='soLuongNhom'
        rules={[
          {
            required: true,
            message: 'Vui lòng nhập dữ liệu',
            type: 'number',
          },
        ]}
      >
        <InputNumber min={2} />
      </Form.Item>

      <Form.Item
        label='Ngày'
        name='ngayBatDau'
        rules={[
          {
            required: true,
            message: 'Vui lòng nhập dữ liệu',
          },
        ]}
      >
        <RangePicker />
      </Form.Item>

      <Form.Item
        wrapperCol={{
          offset: 6,
          span: 16,
        }}
      >
        <Button type='primary' htmlType='submit'>
          Xác nhận
        </Button>
      </Form.Item>
    </Form>
  );
};
export default AddCompetitionForm;

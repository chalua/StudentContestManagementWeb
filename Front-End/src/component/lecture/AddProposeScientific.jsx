import { useQueryClient } from '@tanstack/react-query';
import { Button, DatePicker, Form, Input } from 'antd';
import React from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import useGetCurrentLecture from '../../hooks/lecture/useGetCurrentLecture';
import useProposeSpecific from '../../hooks/lecture/useProposeSpecific';
import { toast } from 'react-toastify';

const formItemLayout = {
  labelCol: { xs: { span: 24 }, sm: { span: 6 } },
  wrapperCol: { xs: { span: 24 }, sm: { span: 14 } },
};

const AddProposeScientific = ({ selectedScientific }) => {
  const [form] = Form.useForm();
  const queryClient = useQueryClient();
  const { data: user } = useGetCurrentLecture();
  const propose = useProposeSpecific();

  const onFinish = async (values) => {
    const data = {
      ...values,
      maGiangVien: user?.data?.maGiangVien,
      maCuocThi: selectedScientific?.id,
    };

    await propose.mutateAsync(data);

    toast.success('Đề xuất thành công!');

    form.resetFields();

    queryClient.invalidateQueries(['rounds', selectedScientific?.id]);
  };

  return (
    <Form
      form={form}
      {...formItemLayout}
      onFinish={onFinish}
      style={{ maxWidth: '100%' }}
      initialValues={{ variant: 'filled' }}
    >
      <Form.Item
        label='Tên đề tài'
        name='tenDeTai'
        rules={[{ required: true, message: 'Vui lòng nhập dữ liệu' }]}
      >
        <Input />
      </Form.Item>

      <Form.Item
        label='Mô Tả'
        name='moTa'
        rules={[{ required: true, message: 'Vui lòng nhập dữ liệu' }]}
      >
        <ReactQuill theme='snow' />
      </Form.Item>

      <Form.Item wrapperCol={{ offset: 6, span: 16 }}>
        <Button type='primary' htmlType='submit'>
          Xác nhận
        </Button>
      </Form.Item>
    </Form>
  );
};

export default AddProposeScientific;

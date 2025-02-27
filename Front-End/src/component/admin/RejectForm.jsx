import React from 'react';
import { Button, Checkbox, Form, Input } from 'antd';
import { useForm } from 'antd/es/form/Form';
import { useQueryClient } from '@tanstack/react-query';
import useLectureReject from '../../hooks/lecture/useLectureReject';
import { toast } from 'react-toastify';
import useRejectSpecific from '../../hooks/specific/useRejectSpecific';

const RejectForm = ({ form, rejectData, setOpen }) => {
  const queryClient = useQueryClient();
  const reject = useRejectSpecific();

  const onFinish = async (values) => {
    await reject.mutateAsync({ ...rejectData, ...values });
    toast.success('Từ chối đề tài thành công');
    queryClient.invalidateQueries(['specificList-propose']);

    setOpen(false);
  };
  const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };

  return (
    <Form
      form={form}
      name='basic'
      labelCol={{
        span: 8,
      }}
      wrapperCol={{
        span: 16,
      }}
      style={{
        maxWidth: 600,
      }}
      initialValues={{
        remember: true,
      }}
      onFinish={onFinish}
      onFinishFailed={onFinishFailed}
      autoComplete='off'
    >
      <Form.Item
        label='Lý do từ chối'
        name='lyDoTuChoi'
        rules={[
          {
            required: true,
            message: 'Vui lòng nhập lý do từ chối',
          },
        ]}
      >
        <Input />
      </Form.Item>
    </Form>
  );
};
export default RejectForm;

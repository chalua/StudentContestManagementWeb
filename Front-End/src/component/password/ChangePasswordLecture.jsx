import React from 'react';
import { Button, Checkbox, Form, Input } from 'antd';
import useChangeAdminPassword from '../../hooks/password/useChangeAdminPassword';
import useGetCurrentAdmin from '../../hooks/admin/useGetCurrentAdmin';
import { toast } from 'react-toastify';
import { useForm } from 'antd/es/form/Form';
import { useNavigate } from 'react-router-dom';
import useGetCurrentLecture from '../../hooks/lecture/useGetCurrentLecture';
import useChangeLecturePassword from '../../hooks/password/useChangeLecturePassword';

const ChangePasswordLecture = () => {
  const [form] = useForm();
  const changePassword = useChangeLecturePassword();
  const { data: lecture } = useGetCurrentLecture();
  const navigate = useNavigate();

  const onFinish = async (values) => {
    const data = { username: lecture?.data?.maGiangVien, ...values };

    await changePassword.mutateAsync(data);

    toast.success('Đổi mật khẩu thành công!');

    form.resetFields();

    navigate('/lecture/sign-in');
  };

  return (
    <Form
      form={form}
      name='basic'
      labelCol={{
        span: 5,
      }}
      wrapperCol={{
        span: 16,
      }}
      //   style={{
      //     maxWidth: 600,
      //   }}
      initialValues={{
        remember: true,
      }}
      onFinish={onFinish}
      autoComplete='off'
    >
      <Form.Item
        label='Mật khẩu cũ'
        name='oldPassword'
        rules={[
          {
            required: true,
            message: 'Vui lòng nhập mật khẩu cũ',
          },
        ]}
      >
        <Input.Password />
      </Form.Item>

      <Form.Item
        label='Mật khẩu mới'
        name='newPassword'
        rules={[
          {
            required: true,
            message: 'Vui lòng nhập mật khẩu mới',
          },
        ]}
      >
        <Input.Password />
      </Form.Item>

      <Form.Item
        label='Xác nhận mật khẩu'
        name='confirmPassword'
        rules={[
          {
            required: true,
            message: 'Vui lòng xác nhận mật khẩu',
          },
        ]}
      >
        <Input.Password />
      </Form.Item>

      <Form.Item
        label={null}
        style={{ display: 'flex', justifyContent: 'center' }}
      >
        <Button type='primary' htmlType='submit'>
          Đổi mật khẩu
        </Button>
      </Form.Item>
    </Form>
  );
};
export default ChangePasswordLecture;

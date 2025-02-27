import { Button, Form, Input } from 'antd';
import { useForm } from 'antd/es/form/Form';
import React from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import useGetCurrentLecture from '../../hooks/lecture/useGetCurrentLecture';
import useChangeStudentPassword from '../../hooks/password/useChangeStudentPassword';
import useGetCurrentStudent from '../../hooks/student/useGetCurrentStudent';

const ChangePasswordStudent = () => {
  const [form] = useForm();
  const changePassword = useChangeStudentPassword();
  const { data: student } = useGetCurrentStudent();
  const navigate = useNavigate();

  const onFinish = async (values) => {
    const data = { username: student?.data?.maSinhVien, ...values };

    await changePassword.mutateAsync(data);

    toast.success('Đổi mật khẩu thành công!');

    form.resetFields();

    navigate('/student/sign-in');
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
export default ChangePasswordStudent;

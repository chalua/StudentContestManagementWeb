import React from 'react';

import { Button, Checkbox, Form, Grid, Input, theme, Typography } from 'antd';

import { LockOutlined, MailOutlined } from '@ant-design/icons';
import useStudentLogin from '../../hooks/student/useStudentLogin';

const { useToken } = theme;
const { useBreakpoint } = Grid;
const { Text, Title, Link } = Typography;

export default function SignIn() {
  const signIn = useStudentLogin();

  const { token } = useToken();
  const screens = useBreakpoint();

  const onFinish = (values) => {
    signIn.mutate(values);
  };

  const styles = {
    container: {
      margin: '0 auto',
      padding: screens.md
        ? `${token.paddingXL}px`
        : `${token.sizeXXL}px ${token.padding}px`,
      width: '380px',
    },
    footer: {
      marginTop: token.marginLG,
      textAlign: 'center',
      width: '100%',
    },
    forgotPassword: {
      float: 'right',
    },
    header: {
      marginBottom: token.marginXL,
      textAlign: 'center',
    },
    section: {
      alignItems: 'center',
      backgroundColor: token.colorBgContainer,
      display: 'flex',
      height: screens.sm ? '100vh' : 'auto',
      padding: screens.md ? `${token.sizeXXL}px 0px` : '0px',
    },
    text: {
      color: token.colorTextSecondary,
    },
    title: {
      fontSize: screens.md ? token.fontSizeHeading2 : token.fontSizeHeading3,
    },
  };

  return (
    <section style={styles.section}>
      <div style={styles.container}>
        <div style={styles.header}>
          <svg
            width='25'
            height='24'
            viewBox='0 0 25 24'
            fill='none'
            xmlns='http://www.w3.org/2000/svg'
          >
            <rect x='0.464294' width='24' height='24' rx='4.8' fill='#1890FF' />
            <path
              d='M14.8643 3.6001H20.8643V9.6001H14.8643V3.6001Z'
              fill='white'
            />
            <path
              d='M10.0643 9.6001H14.8643V14.4001H10.0643V9.6001Z'
              fill='white'
            />
            <path
              d='M4.06427 13.2001H11.2643V20.4001H4.06427V13.2001Z'
              fill='white'
            />
          </svg>

          <Title style={styles.title}>Đăng nhập</Title>
        </div>
        <Form
          name='normal_login'
          initialValues={{
            remember: true,
          }}
          onFinish={onFinish}
          layout='vertical'
          requiredMark='optional'
        >
          <Form.Item
            name='maSinhVien'
            rules={[
              {
                type: 'text',
                required: true,
                message: 'Vui lòng nhập MSSV!',
              },
            ]}
          >
            <Input prefix={<MailOutlined />} placeholder='MSSV' />
          </Form.Item>
          <Form.Item
            name='password'
            rules={[
              {
                required: true,
                message: 'Vui lòng nhập mật khẩu',
              },
            ]}
          >
            <Input.Password
              prefix={<LockOutlined />}
              type='password'
              placeholder='Mật khẩu'
            />
          </Form.Item>

          <Form.Item style={{ marginBottom: '0px' }}>
            <Button block='true' type='primary' htmlType='submit'>
              Đăng Nhập
            </Button>
          </Form.Item>
        </Form>
      </div>
    </section>
  );
}

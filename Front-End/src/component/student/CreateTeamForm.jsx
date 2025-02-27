import React, { useState } from 'react';
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
import useTeamCreate from '../../hooks/team/useTeamCreate';
import { toast } from 'react-toastify';
import useGetCurrentStudent from '../../hooks/student/useGetCurrentStudent';
import { useQueryClient } from '@tanstack/react-query';
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
const CreateTeamForm = ({ handleCancel }) => {
  const { data: student } = useGetCurrentStudent();
  const createTeam = useTeamCreate();
  const queryClient = useQueryClient();

  const onFinish = async (values) => {
    try {
      await createTeam.mutateAsync({
        mssvNhomTruong: student?.data?.maSinhVien,
        ...values,
      });

      toast.success('Đã tạo nhóm thành công');
      handleCancel();
      queryClient.invalidateQueries({
        queryKey: ['team', student?.data?.maSinhVien],
      });
    } catch (error) {
      console.log(error);
    }
  };
  const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };

  return (
    <Form
      {...formItemLayout}
      variant={'filled'}
      style={{
        maxWidth: '100%',
      }}
      initialValues={{
        variant: 'filled',
      }}
      onFinish={onFinish}
      onFinishFailed={onFinishFailed}
    >
      <Form.Item
        label='Tên nhóm'
        name='tenNhom'
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
        label='Loại'
        name='loaiNhom'
        rules={[{ required: true, message: 'Vui lòng chọn loại nhóm!' }]}
      >
        <Select
          options={[
            {
              value: 'THI_DAU',
              label: 'Cuộc thi học thuật / lập trình',
            },
            {
              value: 'NGHIEN_CUU_KHOA_HOC',
              label: 'Nghiên cứu khoa học',
            },
          ]}
        />
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
export default CreateTeamForm;

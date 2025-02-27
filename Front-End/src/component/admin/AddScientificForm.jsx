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
const AddScientificForm = () => {
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
    >
      <Form.Item
        label='Input'
        name='Input'
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
        label='InputNumber'
        name='InputNumber'
        rules={[
          {
            required: true,
            message: 'Vui lòng nhập dữ liệu',
          },
        ]}
      >
        <InputNumber
          style={{
            width: '100%',
          }}
        />
      </Form.Item>

      <Form.Item
        label='Mô tả'
        name='TextArea'
        rules={[
          {
            required: true,
            message: 'Vui lòng nhập dữ liệu',
          },
        ]}
      >
        <Input.TextArea />
      </Form.Item>

      <Form.Item
        label='Mentions'
        name='Mentions'
        rules={[
          {
            required: true,
            message: 'Vui lòng nhập dữ liệu',
          },
        ]}
      >
        <Mentions />
      </Form.Item>

      <Form.Item
        label='Select'
        name='Select'
        rules={[
          {
            required: true,
            message: 'Vui lòng nhập dữ liệu',
          },
        ]}
      >
        <Select />
      </Form.Item>

      <Form.Item
        label='Cascader'
        name='Cascader'
        rules={[
          {
            required: true,
            message: 'Vui lòng nhập dữ liệu',
          },
        ]}
      >
        <Cascader />
      </Form.Item>

      <Form.Item
        label='TreeSelect'
        name='TreeSelect'
        rules={[
          {
            required: true,
            message: 'Vui lòng nhập dữ liệu',
          },
        ]}
      >
        <TreeSelect />
      </Form.Item>

      <Form.Item
        label='RangePicker'
        name='RangePicker'
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
export default AddScientificForm;

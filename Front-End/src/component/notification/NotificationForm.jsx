import React from 'react';
import { Button, Form, Input, Upload } from 'antd';
import ReactQuill from 'react-quill';
import { PlusOutlined } from '@ant-design/icons';
import useCreateNotification from '../../hooks/notification/useCreateNotification';
import { toast } from 'react-toastify';
import { useQueryClient } from '@tanstack/react-query';

const NotificationForm = ({ form, setOpen, create }) => {
  const queryClient = useQueryClient();

  const onFinish = async (values) => {
    const { fileList, pdfList, tieuDe, noiDung } = values;

    const formData = new FormData();
    formData.append('tieuDe', tieuDe);
    formData.append('noiDung', noiDung);

    if (fileList && fileList[0]?.originFileObj) {
      formData.append('hinhAnh', fileList[0].originFileObj);
    }

    if (pdfList && pdfList[0]?.originFileObj) {
      formData.append('file', pdfList[0].originFileObj);
    }

    await create.mutateAsync(formData);
    queryClient.invalidateQueries({ queryKey: ['notifications'] });
    toast.success('Tạo thông báo thành công!');

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
        label='Tiêu đề'
        name='tieuDe'
        rules={[
          {
            required: true,
            message: 'Vui lòng nhập tiêu đề',
          },
        ]}
      >
        <Input />
      </Form.Item>

      <Form.Item
        label='Nội dung'
        name='noiDung'
        rules={[
          {
            required: true,
            message: 'Vui lòng nhập nội dung',
          },
        ]}
      >
        <ReactQuill />
      </Form.Item>

      <Form.Item label='Hình ảnh' name='fileList' valuePropName='fileList'>
        <Upload
          Hành
          động='/upload.do'
          listType='picture-card'
          beforeUpload={() => false}
          accept='image/*'
          maxCount={1}
          onChange={({ fileList }) => {
            form.setFieldsValue({
              fileList: Array.isArray(fileList) ? fileList : [],
            });
          }}
        >
          <button
            style={{
              border: 0,
              background: 'none',
            }}
            type='button'
          >
            <PlusOutlined />
            <div
              style={{
                marginTop: 8,
              }}
            >
              Upload
            </div>
          </button>
        </Upload>
      </Form.Item>

      <Form.Item label='File' name='pdfList' valuePropName='fileList'>
        <Upload
          Hành
          động='/upload.do'
          listType='picture-card'
          beforeUpload={() => false}
          accept='.pdf,.doc,.docx,.xls,.xlsx'
          maxCount={1}
          onChange={({ fileList }) => {
            form.setFieldsValue({
              pdfList: Array.isArray(fileList) ? fileList : [],
            });
          }}
        >
          <button
            style={{
              border: 0,
              background: 'none',
            }}
            type='button'
          >
            <PlusOutlined />
            <div
              style={{
                marginTop: 8,
              }}
            >
              Upload
            </div>
          </button>
        </Upload>
      </Form.Item>
    </Form>
  );
};

export default NotificationForm;

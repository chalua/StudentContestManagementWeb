import React, { useEffect, useState } from 'react';
import { Button, Modal } from 'antd';
import NotificationForm from './NotificationForm';
import { useForm } from 'antd/es/form/Form';
import useCreateNotification from '../../hooks/notification/useCreateNotification';
const NotificationModal = () => {
  const create = useCreateNotification();

  const [form] = useForm();
  const [open, setOpen] = useState(false);

  useEffect(() => {
    if (!open) form.resetFields();
  }, [open]);

  return (
    <>
      <Button type='primary' onClick={() => setOpen(true)}>
        Tạo thông báo
      </Button>
      <Modal
        title='Thông báo'
        centered
        open={open}
        onOk={() => form.submit()}
        okButtonProps={{ disabled: create.loading }}
        onCancel={() => setOpen(false)}
        width={1000}
      >
        <NotificationForm form={form} setOpen={setOpen} create={create} />
      </Modal>
    </>
  );
};
export default NotificationModal;

import { Modal } from 'antd';
import React from 'react';
import ReactQuill from 'react-quill';

const NotificationContentModal = ({ open, setOpen, selectedNotification }) => {
  return (
    <>
      <Modal
        title='Nội dung thông báo'
        centered
        open={open}
        onOk={() => setOpen(false)}
        onCancel={() => setOpen(false)}
        width={1000}
      >
        <ReactQuill value={selectedNotification.noiDung} readOnly />
      </Modal>
    </>
  );
};
export default NotificationContentModal;

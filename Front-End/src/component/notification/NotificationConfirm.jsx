import { Popconfirm } from 'antd';
import React from 'react';
const NotificationConfirm = ({ open, setOpen, handleOk, children }) => {
  const handleCancel = () => {
    setOpen(false);
  };
  return (
    <Popconfirm
      title='Xóa'
      description='Bạn có chắc chắn muốn xóa?'
      open={open}
      onConfirm={handleOk}
      onCancel={handleCancel}
    >
      {children}
    </Popconfirm>
  );
};
export default NotificationConfirm;

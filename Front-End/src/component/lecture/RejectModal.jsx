import { Button, Modal } from 'antd';
import React, { useEffect } from 'react';
import RejectForm from './RejectForm';
import { useForm } from 'antd/es/form/Form';
const RejectModal = ({ open, setOpen, rejectData }) => {
  const [form] = useForm();

  const handleOk = () => {
    form.submit();
    //setOpen(false);
  };
  const handleCancel = () => {
    setOpen(false);
  };

  useEffect(() => {
    if (!open) form.resetFields();
  }, [open]);

  return (
    <>
      <Modal
        open={open}
        title='Từ chối'
        onOk={handleOk}
        onCancel={handleCancel}
        footer={(_, { OkBtn, CancelBtn }) => (
          <>
            <CancelBtn />
            <OkBtn />
          </>
        )}
      >
        <RejectForm form={form} rejectData={rejectData} setOpen={setOpen} />
      </Modal>
    </>
  );
};
export default RejectModal;

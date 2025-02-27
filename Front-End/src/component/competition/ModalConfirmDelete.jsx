import React, { useState } from 'react';
import { Button, Modal, Space } from 'antd';
import useDeleteCompetition from '../../hooks/competition/useDeleteCompetition';
import { toast } from 'react-toastify';
import { useQueryClient } from '@tanstack/react-query';

const ModalConfirmDelete = ({ open, setOpen, selectedCompetition }) => {
  const competitionDelete = useDeleteCompetition();
  const queryClient = useQueryClient();

  const handleOk = async () => {
    await competitionDelete.mutateAsync(selectedCompetition.id);

    toast.success('Xóa cuộc thi thành công');
    setOpen(false);

    queryClient.invalidateQueries([
      'competitionList',
      selectedCompetition.maLoaiCuocThi,
    ]);
  };

  const handleCancel = () => {
    setOpen(false);
  };
  return (
    <>
      <Modal
        open={open}
        title='Xác nhận'
        onOk={handleOk}
        onCancel={handleCancel}
        footer={(_, { OkBtn, CancelBtn }) => (
          <>
            <CancelBtn />
            <OkBtn />
          </>
        )}
      >
        Bạn có chắc chắn muốn xóa cuộc thi: {selectedCompetition.tenCuocThi}
      </Modal>
    </>
  );
};
export default ModalConfirmDelete;

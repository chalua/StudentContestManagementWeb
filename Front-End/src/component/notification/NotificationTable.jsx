import React, { useState } from 'react';
import { Button, Space, Table, Tag } from 'antd';
import useGetNotifications from '../../hooks/notification/useGetNotifications';
import { formatDate } from '../../utils/formatDay';
import NotificationContentModal from './NotificationContentModal';
import NotificationConfirm from './NotificationConfirm';
import useDeleteNotification from '../../hooks/notification/useDeleteNotification';
import { useQueryClient } from '@tanstack/react-query';
import { toast } from 'react-toastify';

const NotificationTable = () => {
  const { data: notifications } = useGetNotifications();
  const deleteNotification = useDeleteNotification();
  const queryClient = useQueryClient();

  const [openContent, setOpenContent] = useState(false);
  const [selectedNotification, setSelectedNotification] = useState({});
  const [openConfirm, setOpenConfirm] = useState(false);

  const columns = [
    {
      title: 'Tiêu đề',
      dataIndex: 'tieuDe',
      key: 'name',
      render: (text) => <a>{text}</a>,
    },
    {
      title: 'Ngày tạo',
      dataIndex: 'ngayTao',
      key: 'ngayTao',
      render: (text) => <span>{formatDate(text)}</span>,
    },
    {
      title: 'Hành động',
      key: 'Hành động',
      render: (_, record) => {
        const handleContent = () => {
          setSelectedNotification(record);
          setOpenContent(true);
        };

        const handleDelete = () => {
          setOpenConfirm(true);
          setSelectedNotification(record);
        };

        const handleOk = async () => {
          await deleteNotification.mutateAsync(record.id);
          toast.success('Xóa thông báo thành công');
          queryClient.invalidateQueries({ queryKey: ['notifications'] });
        };

        return (
          <Space size='middle'>
            <Button color='primary' onClick={handleContent}>
              Nội dung
            </Button>
            <NotificationConfirm
              open={openConfirm && record.id == selectedNotification.id}
              setOpen={setOpenConfirm}
              handleOk={handleOk}
            >
              <Button style={{ color: '#dd4747' }} onClick={handleDelete}>
                Xóa
              </Button>
            </NotificationConfirm>
          </Space>
        );
      },
    },
  ];

  return (
    <>
      <Table columns={columns} dataSource={notifications?.data || []} />
      <NotificationContentModal
        open={openContent}
        setOpen={setOpenContent}
        selectedNotification={selectedNotification}
      />
    </>
  );
};
export default NotificationTable;

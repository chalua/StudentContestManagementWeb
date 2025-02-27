import React from 'react';
import NotificationModal from './NotificationModal';
import NotificationTable from './NotificationTable';

function ManageNotification(props) {
  return (
    <>
      <NotificationModal />
      <NotificationTable />
    </>
  );
}

export default ManageNotification;

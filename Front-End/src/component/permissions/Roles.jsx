import React, { useState } from 'react';
import PermissionsTable from './PermissionsTable';
import { Button, Typography } from 'antd';
import { PlusOutlined } from '@ant-design/icons';
import RolesTable from './RolesTable';
import AddRoleForm from './AddRoleForm';

const { Title } = Typography;

function Roles() {
  const [open, setOpen] = useState(false);
  return (
    <>
      <Button
        type='primary'
        icon={<PlusOutlined />}
        onClick={() => setOpen(true)}
      >
        Thêm Vai Trò
      </Button>
      <Title level={2} style={{ textAlign: 'center' }}>
        Danh sách vai trò
      </Title>
      <RolesTable setOpen={setOpen} />
      <AddRoleForm open={open} setOpen={setOpen} />
    </>
  );
}

export default Roles;

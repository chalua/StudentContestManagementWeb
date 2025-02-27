import React from 'react';
import PermissionsTable from './PermissionsTable';
import { Button, Typography } from 'antd';
import { PlusOutlined } from '@ant-design/icons';

const { Title } = Typography;

function Permissions(props) {
  return (
    <>
      <Title level={2} style={{ textAlign: 'center' }}>
        Danh sách quyền hạn
      </Title>
      <PermissionsTable />
    </>
  );
}

export default Permissions;

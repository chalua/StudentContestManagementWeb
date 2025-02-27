import React, { useState } from 'react';
import {
  Button,
  Collapse,
  Modal,
  Switch,
  Flex,
  Form,
  Input,
  Typography,
} from 'antd';

const { Title } = Typography;

const AddRoleForm = ({ open, setOpen }) => {
  const [permissions, setPermissions] = useState({
    competitions: {
      GET: false,
      POST: false,
      PUT: false,
      DELETE: false,
    },
  });

  const handlePermissionChange = (category, action, checked) => {
    setPermissions((prev) => ({
      ...prev,
      [category]: {
        ...prev[category],
        [action]: checked,
      },
    }));
  };

  const onFinish = (values) => {
    const selectedPermissions = Object.keys(permissions).reduce(
      (acc, category) => {
        const actions = Object.entries(permissions[category])
          .filter(([_, isEnabled]) => isEnabled)
          .map(([action]) => action);

        if (actions.length > 0) {
          acc[category] = actions;
        }
        return acc;
      },
      {}
    );

    console.log('Success:', { ...values, permissions: selectedPermissions });
  };

  const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };

  return (
    <>
      <Modal
        title='Tạo Vai Trò & Quyền'
        centered
        open={open}
        onOk={() => setOpen(false)}
        onCancel={() => setOpen(false)}
        width={1000}
      >
        <Form
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
            label='Tên vai trò'
            name='role'
            rules={[
              {
                required: true,
                message: 'Vui lòng nhập tên vai trò!',
              },
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item label='Quyền hạn' name='permissions'>
            <Collapse
              size='large'
              items={[
                {
                  key: '1',
                  label: 'Cuộc thi',
                  children: (
                    <Flex gap={4} vertical>
                      {/* GET permission */}
                      <Flex align='center' gap={8}>
                        <Switch
                          checked={permissions.competitions.GET}
                          onChange={(checked) =>
                            handlePermissionChange(
                              'competitions',
                              'GET',
                              checked
                            )
                          }
                        />
                        <Title level={5}>GET /api/v1/competitions</Title>
                      </Flex>

                      {/* POST permission */}
                      <Flex align='center' gap={8}>
                        <Switch
                          checked={permissions.competitions.POST}
                          onChange={(checked) =>
                            handlePermissionChange(
                              'competitions',
                              'POST',
                              checked
                            )
                          }
                        />
                        <Title level={5}>POST /api/v1/competitions</Title>
                      </Flex>

                      {/* PUT permission */}
                      <Flex align='center' gap={8}>
                        <Switch
                          checked={permissions.competitions.PUT}
                          onChange={(checked) =>
                            handlePermissionChange(
                              'competitions',
                              'PUT',
                              checked
                            )
                          }
                        />
                        <Title level={5}>PUT /api/v1/competitions</Title>
                      </Flex>

                      {/* DELETE permission */}
                      <Flex align='center' gap={8}>
                        <Switch
                          checked={permissions.competitions.DELETE}
                          onChange={(checked) =>
                            handlePermissionChange(
                              'competitions',
                              'DELETE',
                              checked
                            )
                          }
                        />
                        <Title level={5}>DELETE /api/v1/competitions</Title>
                      </Flex>
                    </Flex>
                  ),
                },
              ]}
            />
          </Form.Item>

          <Form.Item
            wrapperCol={{
              offset: 8,
              span: 16,
            }}
          >
            <Button type='primary' htmlType='submit'>
              Xác nhận
            </Button>
          </Form.Item>
        </Form>
      </Modal>
    </>
  );
};
export default AddRoleForm;

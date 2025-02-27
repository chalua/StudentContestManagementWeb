import React, { useEffect, useState } from 'react';
import { Button, List, Space } from 'antd';
import { useNavigate } from 'react-router-dom';
import SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { renderText } from '../../utils/renderText';
import useGetNotifications from '../../hooks/notification/useGetNotifications'; // assuming useQuery is set up in this hook

const IconText = ({ icon, text }) => (
  <Space>
    {React.createElement(icon)}
    {text}
  </Space>
);

const Notifications = () => {
  const { data: notifications } = useGetNotifications();
  const [realTimeNotifications, setRealTimeNotifications] = useState([]);
  const navigate = useNavigate();

  const data =
    notifications?.data?.map((notification) => ({
      id: notification.id,
      title: notification.tieuDe,
      image: notification.hinhAnhBase64,
      content: notification.noiDung,
    })) || [];

  const combinedNotifications = [...data, ...realTimeNotifications];

  useEffect(() => {
    const socket = new SockJS('http://localhost:8080/ws');
    const stompClient = Stomp.over(socket);

    stompClient.connect({}, (frame) => {
      console.log('Connected to WebSocket: ' + frame);

      stompClient.subscribe('/topic/notifications', (message) => {
        const newNotification = JSON.parse(message.body);

        const mappedNotification = {
          id: newNotification.id,
          title: newNotification.tieuDe,
          image: newNotification.hinhAnhBase64,
          content: newNotification.noiDung,
        };

        setRealTimeNotifications((prevNotifications) => [
          ...prevNotifications,
          mappedNotification,
        ]);
      });
    });

    return () => {
      if (stompClient.connected) {
        stompClient.disconnect();
      }
    };
  }, []);

  if (!notifications || !notifications.data)
    return <div>Loading notifications...</div>;

  return (
    <List
      itemLayout='vertical'
      size='large'
      pagination={{
        onChange: (page) => {
          console.log(page);
        },
        pageSize: 3,
      }}
      dataSource={combinedNotifications}
      renderItem={(item) => (
        <List.Item
          key={item.id}
          actions={[
            <Button
              key='detail'
              color='primary'
              type='primary'
              onClick={() => navigate(`/notifications/${item.id}`)}
            >
              Xem chi tiết
            </Button>,
          ]}
          extra={
            item.image ? (
              <img
                width={272}
                alt='Image'
                src={`data:image/*;base64,${item.image}`}
              />
            ) : null
          }
        >
          <List.Item.Meta title={<a href={item.href}>{item.title}</a>} />
          <div
            dangerouslySetInnerHTML={{
              __html: renderText(item.content),
            }}
          ></div>
        </List.Item>
      )}
    />
  );
};

export default Notifications;

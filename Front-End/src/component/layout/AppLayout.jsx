import {
  AlertOutlined,
  CheckOutlined,
  FieldNumberOutlined,
  HistoryOutlined,
  InfoOutlined,
  NotificationOutlined,
  OrderedListOutlined,
  ProductOutlined,
  TeamOutlined,
  UserAddOutlined,
  UsergroupAddOutlined,
  UserOutlined,
} from '@ant-design/icons';
import { Badge, Breadcrumb, Layout, Menu, theme } from 'antd';
import { useState } from 'react';
import { Outlet, useNavigate } from 'react-router-dom';
import useGetCurrentStudent from '../../hooks/student/useGetCurrentStudent';
import useGetCurrentLecture from '../../hooks/lecture/useGetCurrentLecture';
import useGetCurrentAdmin from '../../hooks/admin/useGetCurrentAdmin';
const { Header, Content, Sider } = Layout;

const items1 = ['Trang chủ'].map((key) => ({
  key,
  label: `${key}`,
}));

const items2 = [
  {
    key: '/',
    label: 'Danh sách cuộc thi',
    icon: <ProductOutlined />,
  },
  {
    key: '/scientific',
    label: 'Nghiên cứu khoa học',
    icon: <ProductOutlined />,
  },
  {
    key: '/notifications',
    label: 'Xem thông báo',
    url: '/student',
    icon: (
      <Badge count={100}>
        <NotificationOutlined />
      </Badge>
    ),
  },
  {
    key: 'student',
    label: 'Sinh viên',
    url: '/student',
    icon: <UserOutlined />,
    children: [
      {
        key: '/student/info',
        label: 'Xem thông tin',
        url: '/student/info',
        icon: <InfoOutlined />,
      },
      {
        key: '/student/history-competition',
        label: 'Lịch sử tham gia cuộc thi',
        url: '/student/history-competition',
        icon: <HistoryOutlined />,
      },
      {
        key: '/student/history-competition-scientific',
        label: 'Lịch sử tham gia NCKH',
        url: '/student/history-competition',
        icon: <HistoryOutlined />,
      },
      {
        key: '/student/team',
        label: 'Nhóm của tôi',
        url: '/student/team',
        icon: <TeamOutlined />,
      },
      {
        key: '/student/invite',
        label: 'Lời mời vào nhóm',
        url: '/student/team',
        icon: <TeamOutlined />,
      },
      {
        key: '/student/change-password',
        label: 'Đổi mật khẩu',
        url: '/student/team',
        icon: <TeamOutlined />,
      },
      {
        key: '/student/sign-in',
        label: 'Đăng xuất',
        url: '/student/team',
        icon: <TeamOutlined />,
      },
    ],
  },
  {
    key: 'lecturer',
    label: 'Giảng viên',
    url: '/lecturer',
    icon: <UserAddOutlined />,
    children: [
      {
        key: '/lecture/my-competitions',
        label: 'Tất cả cuộc thi',
        url: '/lecturer/option1',
        icon: <OrderedListOutlined />,
      },
      {
        key: '/lecture/my-propose-scientific',
        label: 'Đề xuất đề tài',
        url: '/lecturer/option1',
        icon: <OrderedListOutlined />,
      },
      {
        key: '/lecture/analysis',
        label: 'Thống kê',
        url: '/lecturer/option1',
        icon: <OrderedListOutlined />,
      },
      {
        key: '/lecture/change-password',
        label: 'Đổi mật khẩu',
        url: '/student/team',
        icon: <TeamOutlined />,
      },
      {
        key: '/lecture/sign-in',
        label: 'Đăng xuất',
        url: '/student/team',
        icon: <TeamOutlined />,
      },
    ],
  },
  {
    key: 'admin',
    label: 'Quản trị viên',
    url: '/student',
    icon: <UserOutlined />,
    children: [
      {
        key: '/admin/manage-competition',
        label: 'Cuộc thi học thuật',
        url: '/manage-competition',
        icon: <OrderedListOutlined />,
      },
      {
        key: '/admin/manage-competition-project',
        label: 'Cuộc thi dự án',
        url: '/manage-competition',
        icon: <OrderedListOutlined />,
      },
      {
        key: '/admin/manage-scientific',
        label: 'Nghiên cứu khoa học',
        url: '/manage-competition',
        icon: <OrderedListOutlined />,
      },
      {
        key: '/admin/manage-notification',
        label: 'Thông báo',
        url: '/admin/manage-notification',
        icon: <OrderedListOutlined />,
      },
      {
        key: '/admin/analysis',
        label: 'Thống kê',
        url: '/lecturer/option1',
        icon: <OrderedListOutlined />,
      },
      {
        key: '/admin/change-password',
        label: 'Đổi mật khẩu',
        url: '/student/team',
        icon: <TeamOutlined />,
      },
      {
        key: '/admin/sign-in',
        label: 'Đăng xuất',
        url: '/student/team',
        icon: <TeamOutlined />,
      },
      {
        key: '/admin/upload',
        label: 'Tải lên Excel',
        url: '/student/team',
        icon: <TeamOutlined />,
      },
    ],
  },
];

// const items2 = [UserOutlined, LaptopOutlined, NotificationOutlined].map(
//   (icon, index) => {
//     const key = String(index + 1);
//     return {
//       key: `sub${key}`,
//       icon: React.createElement(icon),
//       label: `subnav ${key}`,
//       children: new Array(4).fill(null).map((_, j) => {
//         const subKey = index * 4 + j + 1;
//         return {
//           key: subKey,
//           label: `option${subKey}`,
//         };
//       }),
//     };
//   }
// );

const AppLayout = () => {
  const navigate = useNavigate();
  const [selectedItem, setSelectedItem] = useState('');

  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  const handleClick = (e) => {
    if (e.key === '/student/sign-in') {
      localStorage.removeItem('token');
    }

    if (e.key === '/lecture/sign-in') {
      localStorage.removeItem('token');
    }

    if (e.key === '/admin/sign-in') {
      localStorage.removeItem('token');
    }

    navigate(e.key);
    setSelectedItem(e.key);

    console.log('click ', e);
  };

  const menuItems = items2.map((item) => ({
    key: item.key,
    label: item.label,
    url: item.url,
    icon: item.icon,
    children: item.children,
    // onClick: () => handleMenuClick(item)
  }));

  const { data: studentData } = useGetCurrentStudent();
  const { data: lectureData } = useGetCurrentLecture();
  const { data: adminData } = useGetCurrentAdmin();

  const allowed = ['/', '/scientific'];

  const filteredMenuItems = menuItems.filter((item) => {
    if (
      studentData &&
      studentData.data.maSinhVien &&
      (item.key.startsWith('admin') || item.key.startsWith('lecture'))
    )
      return false;

    if (
      lectureData &&
      lectureData.data.maGiangVien &&
      (item.key.startsWith('student') ||
        item.key.startsWith('admin') ||
        allowed.includes(item.key))
    )
      return false;

    if (
      adminData &&
      adminData.data.userName &&
      (item.key.startsWith('student') ||
        item.key.startsWith('lecture') ||
        allowed.includes(item.key))
    )
      return false;

    return true;
  });

  return (
    <Layout>
      <Header
        style={{
          display: 'flex',
          alignItems: 'center',
        }}
      >
        <div className='demo-logo' />
        <Menu
          theme='dark'
          mode='horizontal'
          items={items1}
          style={{
            flex: 1,
            minWidth: 0,
          }}
        />
      </Header>
      <Layout>
        <Sider
          width={250}
          style={{
            background: colorBgContainer,
          }}
        >
          <Menu
            mode='inline'
            defaultSelectedKeys={['1']}
            defaultOpenKeys={['lecturer', 'admin']}
            style={{
              height: '100%',
              borderRight: 0,
            }}
            selectedKeys={[selectedItem]}
            items={filteredMenuItems}
            onClick={handleClick}
          />
        </Sider>
        <Layout
          style={{
            padding: '0 24px 24px',
          }}
        >
          {/* <Breadcrumb
            items={[
              {
                title: 'Home',
              },
              {
                title: 'Danh sách cuộc thi',
              },
            ]}
            style={{
              margin: '16px 0',
            }}
          /> */}
          <Content
            style={{
              padding: 24,
              margin: 0,
              height: '100vh',
              background: colorBgContainer,
              borderRadius: borderRadiusLG,
            }}
          >
            <Outlet />
          </Content>
        </Layout>
      </Layout>
    </Layout>
  );
};
export default AppLayout;

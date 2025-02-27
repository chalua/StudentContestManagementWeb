import { useMutation } from '@tanstack/react-query';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { adminApi } from '../../api/adminApi';

function useAdminSignIn() {
  const navigate = useNavigate('/');

  return useMutation({
    mutationFn: (data) => adminApi.signIn(data),
    onSuccess: (res) => {
      const token = res?.data?.token;

      toast.success('Đăng nhập thành công!');

      localStorage.setItem('token', token);

      navigate('/admin/manage-competition');
    },
  });
}

export default useAdminSignIn;

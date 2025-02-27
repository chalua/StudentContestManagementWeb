import { useMutation } from '@tanstack/react-query';
import { studentApi } from '../../api/studentClient';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

function useStudentLogin() {
  const navigate = useNavigate('/');

  return useMutation({
    mutationFn: (data) => studentApi.login(data),
    onSuccess: (res) => {
      console.log(res.data);
      const token = res?.data?.token;

      localStorage.setItem('token', token);

      toast.success('Đăng nhập thành công!');

      navigate('/');
    },
  });
}

export default useStudentLogin;

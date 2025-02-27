import { useMutation } from '@tanstack/react-query';
import { studentApi } from '../../api/studentClient';
import { useNavigate } from 'react-router-dom';
import { lectureApi } from '../../api/lectureApi';
import { toast } from 'react-toastify';

function useLectureSignIn() {
  const navigate = useNavigate('/');

  return useMutation({
    mutationFn: (data) => lectureApi.signIn(data),
    onSuccess: (res) => {
      console.log(res.data);
      const token = res?.data?.token;

      toast.success('Đăng nhập thành công!');

      localStorage.setItem('token', token);

      navigate('/lecture/my-competitions');
    },
  });
}

export default useLectureSignIn;

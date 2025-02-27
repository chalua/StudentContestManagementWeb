import { useMutation } from '@tanstack/react-query';
import { notificationApi } from '../../api/notificationApi';

function useCreateNotification() {
  return useMutation({
    mutationFn: notificationApi.create,
  });
}

export default useCreateNotification;

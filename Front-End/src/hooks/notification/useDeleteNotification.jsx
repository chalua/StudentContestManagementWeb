import { useMutation } from '@tanstack/react-query';
import { notificationApi } from '../../api/notificationApi';

function useDeleteNotification() {
  return useMutation({
    mutationFn: notificationApi.delete,
  });
}

export default useDeleteNotification;

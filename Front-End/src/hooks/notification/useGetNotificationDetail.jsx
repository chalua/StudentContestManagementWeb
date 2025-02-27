import { useQuery } from '@tanstack/react-query';
import React from 'react';
import { notificationApi } from '../../api/notificationApi';

function useGetNotificationDetail(id) {
  return useQuery({
    queryKey: ['notifications', id],
    queryFn: () => notificationApi.get(id),
  });
}

export default useGetNotificationDetail;

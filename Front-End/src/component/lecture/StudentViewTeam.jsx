import { Divider, Typography } from 'antd';
import React from 'react';
import { useParams } from 'react-router-dom';
import useViewTeam from '../../hooks/lecture/useViewTeam';
import StudentTeamTable from '../student/StudentTeamTable';

const { Title } = Typography;

function StudentViewTeam(props) {
  const { teamId } = useParams();

  const { data } = useViewTeam(teamId);

  const team = data?.data;

  return (
    <div>
      <React.Fragment key={team?.maNhom}>
        <Title level={3} style={{ textAlign: 'center', marginBottom: 15 }}>
          Tên nhóm: {team?.tenNhom}
        </Title>

        <StudentTeamTable team={team} />
        <Divider />
      </React.Fragment>
    </div>
  );
}

export default StudentViewTeam;

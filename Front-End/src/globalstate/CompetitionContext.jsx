import { 
    createContext, 
    useContext,
    useState 
} from 'react';
import data from '../component/score/CompetitionData';

const CompetitionContext = createContext();

const CompetitionContextProvider = ({ children }) => {
    const [competitionData, setCompetitionData] = useState(data.filter((item) => item.tags.includes('Đang diễn ra')));
    const [selectedCompetition, setSelectedCompetition] = useState(null);

    return (
        <CompetitionContext.Provider value={{ competitionData, selectedCompetition, setSelectedCompetition }}>
            {children}
        </CompetitionContext.Provider>
    );
}

export { CompetitionContext, CompetitionContextProvider };
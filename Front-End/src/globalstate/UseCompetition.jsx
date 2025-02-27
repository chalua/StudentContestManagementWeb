import { useContext } from 'react';
import { CompetitionContext } from './CompetitionContext';

const useCompetition = () => {
    
    const context = useContext(CompetitionContext);
    if (context === undefined) {
        throw new Error('useCompetition must be used within a CompetitionContextProvider');
    }
    return context;
}

export default useCompetition;
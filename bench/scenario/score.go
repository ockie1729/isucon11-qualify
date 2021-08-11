package scenario

import "github.com/isucon/isucandar/score"

// スコアタグの管理

const (
	ScoreStartBenchmark        score.ScoreTag = "0.StartBenchmark        "
	ScoreGraphExcellent        score.ScoreTag = "1.GraphExcellent        "
	ScoreGraphGood             score.ScoreTag = "2.GraphGood             "
	ScoreGraphNormal           score.ScoreTag = "3.GraphNormal           "
	ScoreGraphBad              score.ScoreTag = "4.GraphBad              "
	ScoreGraphWorst            score.ScoreTag = "5.GraphWorst            "
	ScoreReadInfoCondition     score.ScoreTag = "6.ReadInfoCondition     "
	ScoreReadWarningCondition  score.ScoreTag = "7.ReadWarningCondition  "
	ScoreReadCriticalCondition score.ScoreTag = "8.ReadCriticalCondition "
	ScoreNormalUserInitialize  score.ScoreTag = "_1.NormalUserInitialize " //scoreが0のもの
	ScoreViewerInitialize      score.ScoreTag = "_2.ViewerInitialize     "
	ScoreViewerDropout         score.ScoreTag = "_3.ViewerDropout        "
	ScoreRepairIsu             score.ScoreTag = "_4.RepairIsu            "
	ScorePostInfoCondition     score.ScoreTag = "_6.PostInfoCondition    "
	ScorePostWarningCondition  score.ScoreTag = "_7.PostWarningCondition "
	ScorePostCriticalCondition score.ScoreTag = "_8.PostCriticalCondition"
)

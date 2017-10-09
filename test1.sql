select sum(c) total from (
    select count(id) c from top_summary_stats union
    select count(id) c from top_summary_stats_half_day union
    select count(id) c from top_summary_stats_one_hour union
    select count(id) c from top_summary_stats_tier_five_min union
    select count(id) c from top_summary_stats_tier_half_day union
    select count(id) c from top_summary_stats_tier_one_hour union
    select count(id) c from top_summary_stats_app_five_min union
    select count(id) c from top_summary_stats_app_half_day union
    select count(id) c from top_summary_stats_app_one_hour ) t1;

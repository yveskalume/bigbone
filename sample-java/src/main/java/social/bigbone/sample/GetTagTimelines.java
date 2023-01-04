package social.bigbone.sample;

import social.bigbone.MastodonClient;
import social.bigbone.api.Pageable;
import social.bigbone.api.Range;
import social.bigbone.api.entity.Account;
import social.bigbone.api.entity.Status;
import social.bigbone.api.exception.BigboneRequestException;
import social.bigbone.api.method.Timelines;

@SuppressWarnings({"PMD.SystemPrintln", "PMD.AvoidPrintStackTrace"})
public class GetTagTimelines {
    public static void main(final String[] args) {
        final MastodonClient client = new MastodonClient.Builder("mstdn.jp").build();
        final Timelines timelines = new Timelines(client);

        try {
            final Pageable<Status> statuses = timelines.getTagTimeline("mastodon", new Range(), Timelines.StatusOrigin.LOCAL_AND_REMOTE).execute();
            statuses.getPart().forEach(status -> {
                System.out.println("=============");
                final Account account = status.getAccount();
                if (account != null) {
                    System.out.println(account.getDisplayName());
                }
                System.out.println(status.getContent());
                System.out.println(status.isReblogged());
            });
        } catch (BigboneRequestException e) {
            e.printStackTrace();
        }
    }
}

package com.apps.rafal.zientara.songs.core.sources;

import com.apps.rafal.zientara.songs.core.criteries.SongSourceCriteria;
import com.apps.rafal.zientara.songs.core.model.SongModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evil on 01.09.2017.
 */

public class SourcesSynchronizer {
    private static final int SYNC_TIMEOUT = 1000;
    private int threadsCount;
    private int readyThreads;
    private final List<SongModel> output;

    public SourcesSynchronizer() {
        output = new ArrayList<>();
    }

    private void resetCounters() {
        threadsCount = 0;
        readyThreads = 0;
    }

    public List<SongModel> getSongModelsSync(SongSourceCriteria criteria, List<SongsSource> songsSources) {
        output.clear();
        resetCounters();
//        Object sync = new Object()

        List<SyncThread> syncThreads = new ArrayList<>();
        for (SongsSource source : songsSources) {
            if (source.isEnabled()) {
//                output.addAll(criteria.getData(source));
                syncThreads.add(new SyncThread(source, criteria, output));
                threadsCount++;
            }
        }

        for (SyncThread thread : syncThreads) {
            thread.start();
        }

        synchronized (output) {
            try {
                output.wait(SYNC_TIMEOUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return output;
    }

    private class SyncThread extends Thread {
        private SongSourceCriteria criteria;
        private final List<SongModel> output;
        private SongsSource source;

        SyncThread(SongsSource source, SongSourceCriteria criteria, List<SongModel> output) {
            this.source = source;
            this.criteria = criteria;
            this.output = output;
        }

        @Override
        public void run() {
            super.run();
            List<SongModel> songModels = criteria.getData(source);
            readyThreads++;
            synchronized (output) {
                output.addAll(songModels);
            }
            if (allThreadsReady()) {
                output.notify();
            }
        }
    }

    private boolean allThreadsReady() {
        return readyThreads == threadsCount;
    }
}

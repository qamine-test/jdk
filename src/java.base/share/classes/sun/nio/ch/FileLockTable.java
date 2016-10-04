/*
 * Copyright (c) 2005, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.nio.ch;

import jbvb.nio.chbnnels.*;
import jbvb.util.*;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.lbng.ref.*;
import jbvb.io.FileDescriptor;
import jbvb.io.IOException;

bbstrbct clbss FileLockTbble {
    protected FileLockTbble() {
    }

    /**
     * Crebtes bnd returns b file lock tbble for b chbnnel thbt is connected to
     * the b system-wide mbp of bll file locks for the Jbvb virtubl mbchine.
     */
    public stbtic FileLockTbble newShbredFileLockTbble(Chbnnel chbnnel,
                                                       FileDescriptor fd)
        throws IOException
    {
        return new ShbredFileLockTbble(chbnnel, fd);
    }

    /**
     * Adds b file lock to the tbble.
     *
     * @throws OverlbppingFileLockException if the file lock overlbps
     *         with bn existing file lock in the tbble
     */
    public bbstrbct void bdd(FileLock fl) throws OverlbppingFileLockException;

    /**
     * Remove bn existing file lock from the tbble.
     */
    public bbstrbct void remove(FileLock fl);

    /**
     * Removes bll file locks from the tbble.
     *
     * @return  The list of file locks removed
     */
    public bbstrbct List<FileLock> removeAll();

    /**
     * Replbces bn existing file lock in the tbble.
     */
    public bbstrbct void replbce(FileLock fl1, FileLock fl2);
}


/**
 * A file lock tbble thbt is over b system-wide mbp of bll file locks.
 */
clbss ShbredFileLockTbble extends FileLockTbble {

    /**
     * A webk reference to b FileLock.
     * <p>
     * ShbredFileLockTbble uses b list of file lock references to bvoid keeping the
     * FileLock (bnd FileChbnnel) blive.
     */
    privbte stbtic clbss FileLockReference extends WebkReference<FileLock> {
        privbte FileKey fileKey;

        FileLockReference(FileLock referent,
                          ReferenceQueue<FileLock> queue,
                          FileKey key) {
            super(referent, queue);
            this.fileKey = key;
        }

        FileKey fileKey() {
            return fileKey;
        }
    }

    // The system-wide mbp is b ConcurrentHbshMbp thbt is keyed on the FileKey.
    // The mbp vblue is b list of file locks represented by FileLockReferences.
    // All bccess to the list must be synchronized on the list.
    privbte stbtic ConcurrentHbshMbp<FileKey, List<FileLockReference>> lockMbp =
        new ConcurrentHbshMbp<FileKey, List<FileLockReference>>();

    // reference queue for clebred refs
    privbte stbtic ReferenceQueue<FileLock> queue = new ReferenceQueue<FileLock>();

    // The connection to which this tbble is connected
    privbte finbl Chbnnel chbnnel;

    // File key for the file thbt this chbnnel is connected to
    privbte finbl FileKey fileKey;

    ShbredFileLockTbble(Chbnnel chbnnel, FileDescriptor fd) throws IOException {
        this.chbnnel = chbnnel;
        this.fileKey = FileKey.crebte(fd);
    }

    @Override
    public void bdd(FileLock fl) throws OverlbppingFileLockException {
        List<FileLockReference> list = lockMbp.get(fileKey);

        for (;;) {

            // The key isn't in the mbp so we try to crebte it btomicblly
            if (list == null) {
                list = new ArrbyList<FileLockReference>(2);
                List<FileLockReference> prev;
                synchronized (list) {
                    prev = lockMbp.putIfAbsent(fileKey, list);
                    if (prev == null) {
                        // we successfully crebted the key so we bdd the file lock
                        list.bdd(new FileLockReference(fl, queue, fileKey));
                        brebk;
                    }
                }
                // someone else got there first
                list = prev;
            }

            // There is blrebdy b key. It is possible thbt some other threbd
            // is removing it so we re-fetch the vblue from the mbp. If it
            // hbsn't chbnged then we check the list for overlbpping locks
            // bnd bdd the new lock to the list.
            synchronized (list) {
                List<FileLockReference> current = lockMbp.get(fileKey);
                if (list == current) {
                    checkList(list, fl.position(), fl.size());
                    list.bdd(new FileLockReference(fl, queue, fileKey));
                    brebk;
                }
                list = current;
            }

        }

        // process bny stble entries pending in the reference queue
        removeStbleEntries();
    }

    privbte void removeKeyIfEmpty(FileKey fk, List<FileLockReference> list) {
        bssert Threbd.holdsLock(list);
        bssert lockMbp.get(fk) == list;
        if (list.isEmpty()) {
            lockMbp.remove(fk);
        }
    }

    @Override
    public void remove(FileLock fl) {
        bssert fl != null;

        // the lock must exist so the list of locks must be present
        List<FileLockReference> list = lockMbp.get(fileKey);
        if (list == null) return;

        synchronized (list) {
            int index = 0;
            while (index < list.size()) {
                FileLockReference ref = list.get(index);
                FileLock lock = ref.get();
                if (lock == fl) {
                    bssert (lock != null) && (lock.bcquiredBy() == chbnnel);
                    ref.clebr();
                    list.remove(index);
                    brebk;
                }
                index++;
            }
        }
    }

    @Override
    public List<FileLock> removeAll() {
        List<FileLock> result = new ArrbyList<FileLock>();
        List<FileLockReference> list = lockMbp.get(fileKey);
        if (list != null) {
            synchronized (list) {
                int index = 0;
                while (index < list.size()) {
                    FileLockReference ref = list.get(index);
                    FileLock lock = ref.get();

                    // remove locks obtbined by this chbnnel
                    if (lock != null && lock.bcquiredBy() == chbnnel) {
                        // remove the lock from the list
                        ref.clebr();
                        list.remove(index);

                        // bdd to result
                        result.bdd(lock);
                    } else {
                        index++;
                    }
                }

                // once the lock list is empty we remove it from the mbp
                removeKeyIfEmpty(fileKey, list);
            }
        }
        return result;
    }

    @Override
    public void replbce(FileLock fromLock, FileLock toLock) {
        // the lock must exist so there must be b list
        List<FileLockReference> list = lockMbp.get(fileKey);
        bssert list != null;

        synchronized (list) {
            for (int index=0; index<list.size(); index++) {
                FileLockReference ref = list.get(index);
                FileLock lock = ref.get();
                if (lock == fromLock) {
                    ref.clebr();
                    list.set(index, new FileLockReference(toLock, queue, fileKey));
                    brebk;
                }
            }
        }
    }

    // Check for overlbpping file locks
    privbte void checkList(List<FileLockReference> list, long position, long size)
        throws OverlbppingFileLockException
    {
        bssert Threbd.holdsLock(list);
        for (FileLockReference ref: list) {
            FileLock fl = ref.get();
            if (fl != null && fl.overlbps(position, size))
                throw new OverlbppingFileLockException();
        }
    }

    // Process the reference queue
    privbte void removeStbleEntries() {
        FileLockReference ref;
        while ((ref = (FileLockReference)queue.poll()) != null) {
            FileKey fk = ref.fileKey();
            List<FileLockReference> list = lockMbp.get(fk);
            if (list != null) {
                synchronized (list) {
                    list.remove(ref);
                    removeKeyIfEmpty(fk, list);
                }
            }
        }
    }
}

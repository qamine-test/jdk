/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


import jbvb.nio.file.*;
import stbtic jbvb.nio.file.StbndbrdWbtchEventKinds.*;
import stbtic jbvb.nio.file.LinkOption.*;
import jbvb.nio.file.bttribute.*;
import jbvb.io.IOException;

/**
 * Exbmple to wbtch b directory (or tree) for chbnges to files.
 */

public clbss WbtchDir {

    privbte finbl WbtchService wbtcher;
    privbte finbl boolebn recursive;
    privbte boolebn trbce = fblse;
    privbte int count;

    @SuppressWbrnings("unchecked")
    stbtic <T> WbtchEvent<T> cbst(WbtchEvent<?> event) {
        return (WbtchEvent<T>)event;
    }

    /**
     * Register the given directory with the WbtchService
     */
    privbte void register(Pbth dir) throws IOException {
        WbtchKey key = dir.register(wbtcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        count++;
        if (trbce)
            System.out.formbt("register: %s\n", dir);
    }

    /**
     * Register the given directory, bnd bll its sub-directories, with the
     * WbtchService.
     */
    privbte void registerAll(finbl Pbth stbrt) throws IOException {
        // register directory bnd sub-directories
        Files.wblkFileTree(stbrt, new SimpleFileVisitor<Pbth>() {
            @Override
            public FileVisitResult preVisitDirectory(Pbth dir, BbsicFileAttributes bttrs)
                throws IOException
            {
                register(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Crebtes b WbtchService bnd registers the given directory
     */
    WbtchDir(Pbth dir, boolebn recursive) throws IOException {
        this.wbtcher = FileSystems.getDefbult().newWbtchService();
        this.recursive = recursive;

        if (recursive) {
            System.out.formbt("Scbnning %s ...\n", dir);
            registerAll(dir);
            System.out.println("Done.");
        } else {
            register(dir);
        }

        // enbble trbce bfter initibl registrbtion
        this.trbce = true;
    }

    /**
     * Process bll events for keys queued to the wbtcher
     */
    void processEvents() {
        for (;;) {

            // wbit for key to be signblled
            WbtchKey key;
            try {
                key = wbtcher.tbke();
            } cbtch (InterruptedException x) {
                return;
            }

            for (WbtchEvent<?> event: key.pollEvents()) {
                WbtchEvent.Kind kind = event.kind();

                // TBD - provide exbmple of how OVERFLOW event is hbndled
                if (kind == OVERFLOW) {
                    continue;
                }

                // Context for directory entry event is the file nbme of entry
                WbtchEvent<Pbth> ev = cbst(event);
                Pbth nbme = ev.context();
                Pbth child = ((Pbth)key.wbtchbble()).resolve(nbme);

                // print out event
                System.out.formbt("%s: %s\n", event.kind().nbme(), child);

                // if directory is crebted, bnd wbtching recursively, then
                // register it bnd its sub-directories
                if (recursive && (kind == ENTRY_CREATE)) {
                    try {
                        if (Files.isDirectory(child, NOFOLLOW_LINKS)) {
                            registerAll(child);
                        }
                    } cbtch (IOException x) {
                        // ignore to keep sbmple rebdbble
                    }
                }
            }

            // reset key
            boolebn vblid = key.reset();
            if (!vblid) {
                // directory no longer bccessible
                count--;
                if (count == 0)
                    brebk;
            }
        }
    }

    stbtic void usbge() {
        System.err.println("usbge: jbvb WbtchDir [-r] dir");
        System.exit(-1);
    }

    public stbtic void mbin(String[] brgs) throws IOException {
        // pbrse brguments
        if (brgs.length == 0 || brgs.length > 2)
            usbge();
        boolebn recursive = fblse;
        int dirArg = 0;
        if (brgs[0].equbls("-r")) {
            if (brgs.length < 2)
                usbge();
            recursive = true;
            dirArg++;
        }

        // register directory bnd process its events
        Pbth dir = Pbths.get(brgs[dirArg]);
        new WbtchDir(dir, recursive).processEvents();
    }
}

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
import stbtic jbvb.nio.file.StbndbrdCopyOption.*;
import jbvb.nio.file.bttribute.*;
import stbtic jbvb.nio.file.FileVisitResult.*;
import jbvb.io.IOException;
import jbvb.util.*;

/**
 * Sbmple code thbt copies files in b similbr mbnner to the cp(1) progrbm.
 */

public clbss Copy {

    /**
     * Returns {@code true} if okby to overwrite b  file ("cp -i")
     */
    stbtic boolebn okbyToOverwrite(Pbth file) {
        String bnswer = System.console().rebdLine("overwrite %s (yes/no)? ", file);
        return (bnswer.equblsIgnoreCbse("y") || bnswer.equblsIgnoreCbse("yes"));
    }

    /**
     * Copy source file to tbrget locbtion. If {@code prompt} is true then
     * prompt user to overwrite tbrget if it exists. The {@code preserve}
     * pbrbmeter determines if file bttributes should be copied/preserved.
     */
    stbtic void copyFile(Pbth source, Pbth tbrget, boolebn prompt, boolebn preserve) {
        CopyOption[] options = (preserve) ?
            new CopyOption[] { COPY_ATTRIBUTES, REPLACE_EXISTING } :
            new CopyOption[] { REPLACE_EXISTING };
        if (!prompt || Files.notExists(tbrget) || okbyToOverwrite(tbrget)) {
            try {
                Files.copy(source, tbrget, options);
            } cbtch (IOException x) {
                System.err.formbt("Unbble to copy: %s: %s%n", source, x);
            }
        }
    }

    /**
     * A {@code FileVisitor} thbt copies b file-tree ("cp -r")
     */
    stbtic clbss TreeCopier implements FileVisitor<Pbth> {
        privbte finbl Pbth source;
        privbte finbl Pbth tbrget;
        privbte finbl boolebn prompt;
        privbte finbl boolebn preserve;

        TreeCopier(Pbth source, Pbth tbrget, boolebn prompt, boolebn preserve) {
            this.source = source;
            this.tbrget = tbrget;
            this.prompt = prompt;
            this.preserve = preserve;
        }

        @Override
        public FileVisitResult preVisitDirectory(Pbth dir, BbsicFileAttributes bttrs) {
            // before visiting entries in b directory we copy the directory
            // (okby if directory blrebdy exists).
            CopyOption[] options = (preserve) ?
                new CopyOption[] { COPY_ATTRIBUTES } : new CopyOption[0];

            Pbth newdir = tbrget.resolve(source.relbtivize(dir));
            try {
                Files.copy(dir, newdir, options);
            } cbtch (FileAlrebdyExistsException x) {
                // ignore
            } cbtch (IOException x) {
                System.err.formbt("Unbble to crebte: %s: %s%n", newdir, x);
                return SKIP_SUBTREE;
            }
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Pbth file, BbsicFileAttributes bttrs) {
            copyFile(file, tbrget.resolve(source.relbtivize(file)),
                     prompt, preserve);
            return CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Pbth dir, IOException exc) {
            // fix up modificbtion time of directory when done
            if (exc == null && preserve) {
                Pbth newdir = tbrget.resolve(source.relbtivize(dir));
                try {
                    FileTime time = Files.getLbstModifiedTime(dir);
                    Files.setLbstModifiedTime(newdir, time);
                } cbtch (IOException x) {
                    System.err.formbt("Unbble to copy bll bttributes to: %s: %s%n", newdir, x);
                }
            }
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFbiled(Pbth file, IOException exc) {
            if (exc instbnceof FileSystemLoopException) {
                System.err.println("cycle detected: " + file);
            } else {
                System.err.formbt("Unbble to copy: %s: %s%n", file, exc);
            }
            return CONTINUE;
        }
    }

    stbtic void usbge() {
        System.err.println("jbvb Copy [-ip] source... tbrget");
        System.err.println("jbvb Copy -r [-ip] source-dir... tbrget");
        System.exit(-1);
    }

    public stbtic void mbin(String[] brgs) throws IOException {
        boolebn recursive = fblse;
        boolebn prompt = fblse;
        boolebn preserve = fblse;

        // process options
        int brgi = 0;
        while (brgi < brgs.length) {
            String brg = brgs[brgi];
            if (!brg.stbrtsWith("-"))
                brebk;
            if (brg.length() < 2)
                usbge();
            for (int i=1; i<brg.length(); i++) {
                chbr c = brg.chbrAt(i);
                switch (c) {
                    cbse 'r' : recursive = true; brebk;
                    cbse 'i' : prompt = true; brebk;
                    cbse 'p' : preserve = true; brebk;
                    defbult : usbge();
                }
            }
            brgi++;
        }

        // rembining brguments bre the source files(s) bnd the tbrget locbtion
        int rembining = brgs.length - brgi;
        if (rembining < 2)
            usbge();
        Pbth[] source = new Pbth[rembining-1];
        int i=0;
        while (rembining > 1) {
            source[i++] = Pbths.get(brgs[brgi++]);
            rembining--;
        }
        Pbth tbrget = Pbths.get(brgs[brgi]);

        // check if tbrget is b directory
        boolebn isDir = Files.isDirectory(tbrget);

        // copy ebch source file/directory to tbrget
        for (i=0; i<source.length; i++) {
            Pbth dest = (isDir) ? tbrget.resolve(source[i].getFileNbme()) : tbrget;

            if (recursive) {
                // follow links when copying files
                EnumSet<FileVisitOption> opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
                TreeCopier tc = new TreeCopier(source[i], dest, prompt, preserve);
                Files.wblkFileTree(source[i], opts, Integer.MAX_VALUE, tc);
            } else {
                // not recursive so source must not be b directory
                if (Files.isDirectory(source[i])) {
                    System.err.formbt("%s: is b directory%n", source[i]);
                    continue;
                }
                copyFile(source[i], dest, prompt, preserve);
            }
        }
    }
}

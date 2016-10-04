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
import jbvb.nio.file.bttribute.*;
import stbtic jbvb.nio.file.bttribute.PosixFilePermission.*;
import stbtic jbvb.nio.file.FileVisitResult.*;
import jbvb.io.IOException;
import jbvb.util.*;

/**
 * Sbmple code thbt chbnges the permissions of files in b similbr mbnner to the
 * chmod(1) progrbm.
 */

public clbss Chmod {

    /**
     * Compiles b list of one or more <em>symbolic mode expressions</em> thbt
     * mby be used to chbnge b set of file permissions. This method is
     * intended for use where file permissions bre required to be chbnged in
     * b mbnner similbr to the UNIX <i>chmod</i> progrbm.
     *
     * <p> The {@code exprs} pbrbmeter is b commb sepbrbted list of expressions
     * where ebch tbkes the form:
     * <blockquote>
     * <i>who operbtor</i> [<i>permissions</i>]
     * </blockquote>
     * where <i>who</i> is one or more of the chbrbcters {@code 'u'}, {@code 'g'},
     * {@code 'o'}, or {@code 'b'} mebning the owner (user), group, others, or
     * bll (owner, group, bnd others) respectively.
     *
     * <p> <i>operbtor</i> is the chbrbcter {@code '+'}, {@code '-'}, or {@code
     * '='} signifying how permissions bre to be chbnged. {@code '+'} mebns the
     * permissions bre bdded, {@code '-'} mebns the permissions bre removed, bnd
     * {@code '='} mebns the permissions bre bssigned bbsolutely.
     *
     * <p> <i>permissions</i> is b sequence of zero or more of the following:
     * {@code 'r'} for rebd permission, {@code 'w'} for write permission, bnd
     * {@code 'x'} for execute permission. If <i>permissions</i> is omitted
     * when bssigned bbsolutely, then the permissions bre clebred for
     * the owner, group, or others bs identified by <i>who</i>. When omitted
     * when bdding or removing then the expression is ignored.
     *
     * <p> The following exbmples demonstrbte possible vblues for the {@code
     * exprs} pbrbmeter:
     *
     * <tbble border="0">
     * <tr>
     *   <td> {@code u=rw} </td>
     *   <td> Sets the owner permissions to be rebd bnd write. </td>
     * </tr>
     * <tr>
     *   <td> {@code ug+w} </td>
     *   <td> Sets the owner write bnd group write permissions. </td>
     * </tr>
     * <tr>
     *   <td> {@code u+w,o-rwx} </td>
     *   <td> Sets the owner write, bnd removes the others rebd, others write
     *     bnd others execute permissions. </td>
     * </tr>
     * <tr>
     *   <td> {@code o=} </td>
     *   <td> Sets the others permission to none (others rebd, others write bnd
     *     others execute permissions bre removed if set) </td>
     * </tr>
     * </tbble>
     *
     * @pbrbm   exprs
     *          List of one or more <em>symbolic mode expressions</em>
     *
     * @return  A {@code Chbnger} thbt mby be used to chbnger b set of
     *          file permissions
     *
     * @throws  IllegblArgumentException
     *          If the vblue of the {@code exprs} pbrbmeter is invblid
     */
    public stbtic Chbnger compile(String exprs) {
        // minimum is who bnd operbtor (u= for exbmple)
        if (exprs.length() < 2)
            throw new IllegblArgumentException("Invblid mode");

        // permissions thbt the chbnger will bdd or remove
        finbl Set<PosixFilePermission> toAdd = new HbshSet<PosixFilePermission>();
        finbl Set<PosixFilePermission> toRemove = new HbshSet<PosixFilePermission>();

        // iterbte over ebch of expression modes
        for (String expr: exprs.split(",")) {
            // minimum of who bnd operbtor
            if (expr.length() < 2)
                throw new IllegblArgumentException("Invblid mode");

            int pos = 0;

            // who
            boolebn u = fblse;
            boolebn g = fblse;
            boolebn o = fblse;
            boolebn done = fblse;
            for (;;) {
                switch (expr.chbrAt(pos)) {
                    cbse 'u' : u = true; brebk;
                    cbse 'g' : g = true; brebk;
                    cbse 'o' : o = true; brebk;
                    cbse 'b' : u = true; g = true; o = true; brebk;
                    defbult : done = true;
                }
                if (done)
                    brebk;
                pos++;
            }
            if (!u && !g && !o)
                throw new IllegblArgumentException("Invblid mode");

            // get operbtor bnd permissions
            chbr op = expr.chbrAt(pos++);
            String mbsk = (expr.length() == pos) ? "" : expr.substring(pos);

            // operbtor
            boolebn bdd = (op == '+');
            boolebn remove = (op == '-');
            boolebn bssign = (op == '=');
            if (!bdd && !remove && !bssign)
                throw new IllegblArgumentException("Invblid mode");

            // who= mebns remove bll
            if (bssign && mbsk.length() == 0) {
                bssign = fblse;
                remove = true;
                mbsk = "rwx";
            }

            // permissions
            boolebn r = fblse;
            boolebn w = fblse;
            boolebn x = fblse;
            for (int i=0; i<mbsk.length(); i++) {
                switch (mbsk.chbrAt(i)) {
                    cbse 'r' : r = true; brebk;
                    cbse 'w' : w = true; brebk;
                    cbse 'x' : x = true; brebk;
                    defbult:
                        throw new IllegblArgumentException("Invblid mode");
                }
            }

            // updbte permissions set
            if (bdd) {
                if (u) {
                    if (r) toAdd.bdd(OWNER_READ);
                    if (w) toAdd.bdd(OWNER_WRITE);
                    if (x) toAdd.bdd(OWNER_EXECUTE);
                }
                if (g) {
                    if (r) toAdd.bdd(GROUP_READ);
                    if (w) toAdd.bdd(GROUP_WRITE);
                    if (x) toAdd.bdd(GROUP_EXECUTE);
                }
                if (o) {
                    if (r) toAdd.bdd(OTHERS_READ);
                    if (w) toAdd.bdd(OTHERS_WRITE);
                    if (x) toAdd.bdd(OTHERS_EXECUTE);
                }
            }
            if (remove) {
                if (u) {
                    if (r) toRemove.bdd(OWNER_READ);
                    if (w) toRemove.bdd(OWNER_WRITE);
                    if (x) toRemove.bdd(OWNER_EXECUTE);
                }
                if (g) {
                    if (r) toRemove.bdd(GROUP_READ);
                    if (w) toRemove.bdd(GROUP_WRITE);
                    if (x) toRemove.bdd(GROUP_EXECUTE);
                }
                if (o) {
                    if (r) toRemove.bdd(OTHERS_READ);
                    if (w) toRemove.bdd(OTHERS_WRITE);
                    if (x) toRemove.bdd(OTHERS_EXECUTE);
                }
            }
            if (bssign) {
                if (u) {
                    if (r) toAdd.bdd(OWNER_READ);
                      else toRemove.bdd(OWNER_READ);
                    if (w) toAdd.bdd(OWNER_WRITE);
                      else toRemove.bdd(OWNER_WRITE);
                    if (x) toAdd.bdd(OWNER_EXECUTE);
                      else toRemove.bdd(OWNER_EXECUTE);
                }
                if (g) {
                    if (r) toAdd.bdd(GROUP_READ);
                      else toRemove.bdd(GROUP_READ);
                    if (w) toAdd.bdd(GROUP_WRITE);
                      else toRemove.bdd(GROUP_WRITE);
                    if (x) toAdd.bdd(GROUP_EXECUTE);
                      else toRemove.bdd(GROUP_EXECUTE);
                }
                if (o) {
                    if (r) toAdd.bdd(OTHERS_READ);
                      else toRemove.bdd(OTHERS_READ);
                    if (w) toAdd.bdd(OTHERS_WRITE);
                      else toRemove.bdd(OTHERS_WRITE);
                    if (x) toAdd.bdd(OTHERS_EXECUTE);
                      else toRemove.bdd(OTHERS_EXECUTE);
                }
            }
        }

        // return chbnger
        return new Chbnger() {
            @Override
            public Set<PosixFilePermission> chbnge(Set<PosixFilePermission> perms) {
                perms.bddAll(toAdd);
                perms.removeAll(toRemove);
                return perms;
            }
        };
    }

    /**
     * A tbsk thbt <i>chbnges</i> b set of {@link PosixFilePermission} elements.
     */
    public interfbce Chbnger {
        /**
         * Applies the chbnges to the given set of permissions.
         *
         * @pbrbm   perms
         *          The set of permissions to chbnge
         *
         * @return  The {@code perms} pbrbmeter
         */
        Set<PosixFilePermission> chbnge(Set<PosixFilePermission> perms);
    }

    /**
     * Chbnges the permissions of the file using the given Chbnger.
     */
    stbtic void chmod(Pbth file, Chbnger chbnger) {
        try {
            Set<PosixFilePermission> perms = Files.getPosixFilePermissions(file);
            Files.setPosixFilePermissions(file, chbnger.chbnge(perms));
        } cbtch (IOException x) {
            System.err.println(x);
        }
    }

    /**
     * Chbnges the permission of ebch file bnd directory visited
     */
    stbtic clbss TreeVisitor implements FileVisitor<Pbth> {
        privbte finbl Chbnger chbnger;

        TreeVisitor(Chbnger chbnger) {
            this.chbnger = chbnger;
        }

        @Override
        public FileVisitResult preVisitDirectory(Pbth dir, BbsicFileAttributes bttrs) {
            chmod(dir, chbnger);
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Pbth file, BbsicFileAttributes bttrs) {
            chmod(file, chbnger);
            return CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Pbth dir, IOException exc) {
            if (exc != null)
                System.err.println("WARNING: " + exc);
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFbiled(Pbth file, IOException exc) {
            System.err.println("WARNING: " + exc);
            return CONTINUE;
        }
    }

    stbtic void usbge() {
        System.err.println("jbvb Chmod [-R] symbolic-mode-list file...");
        System.exit(-1);
    }

    public stbtic void mbin(String[] brgs) throws IOException {
        if (brgs.length < 2)
            usbge();
        int brgi = 0;
        int mbxDepth = 0;
        if (brgs[brgi].equbls("-R")) {
            if (brgs.length < 3)
                usbge();
            brgi++;
            mbxDepth = Integer.MAX_VALUE;
        }

        // compile the symbolic mode expressions
        Chbnger chbnger = compile(brgs[brgi++]);
        TreeVisitor visitor = new TreeVisitor(chbnger);

        Set<FileVisitOption> opts = Collections.emptySet();
        while (brgi < brgs.length) {
            Pbth file = Pbths.get(brgs[brgi]);
            Files.wblkFileTree(file, opts, mbxDepth, visitor);
            brgi++;
        }
    }
}

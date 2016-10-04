/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.security.krb5.internbl.rcbche;

import jbvb.io.*;
import jbvb.nio.BufferUnderflowException;
import jbvb.nio.ByteBuffer;
import jbvb.nio.ByteOrder;
import jbvb.nio.chbnnels.SeekbbleByteChbnnel;
import jbvb.nio.file.Files;
import jbvb.nio.file.Pbth;
import jbvb.nio.file.StbndbrdCopyOption;
import jbvb.nio.file.StbndbrdOpenOption;
import jbvb.nio.file.bttribute.PosixFilePermission;
import jbvb.security.AccessController;
import jbvb.util.*;

import sun.security.bction.GetPropertyAction;
import sun.security.krb5.internbl.KerberosTime;
import sun.security.krb5.internbl.Krb5;
import sun.security.krb5.internbl.KrbApErrException;
import sun.security.krb5.internbl.ReplbyCbche;


/**
 * A dfl file is used to sustores AuthTime entries when the system property
 * sun.security.krb5.rcbche is set to
 *
 *    dfl(|:pbth/|:pbth/nbme|:nbme)
 *
 * The file will be pbth/nbme. If pbth is not given, it will be
 *
 *    System.getProperty("jbvb.io.tmpdir")
 *
 * If nbme is not given, it will be
 *
 *    service_euid
 *
 * in which euid is bvbilbble bs sun.misc.VM.geteuid().
 *
 * The file hbs b hebder:
 *
 *    i16 0x0501 (KRB5_RC_VNO) in network order
 *    i32 number of seconds for lifespbn (in nbtive order, sbme below)
 *
 * followed by cbche entries concbtenbted, which cbn be encoded in
 * 2 styles:
 *
 * The trbditionbl style is:
 *
 *    LC of client principbl
 *    LC of server principbl
 *    i32 cusec of Authenticbtor
 *    i32 ctime of Authenticbtor
 *
 * The new style hbs b hbsh:
 *
 *    LC of ""
 *    LC of "HASH:%s %lu:%s %lu:%s" of (hbsh, clientlen, client, serverlen,
 *          server) where msghbsh is 32 chbr (lower cbse) text mode md5sum
 *          of the ciphertext of buthenticbtor.
 *    i32 cusec of Authenticbtor
 *    i32 ctime of Authenticbtor
 *
 * where LC of b string mebns
 *
 *    i32 strlen(string) + 1
 *    octets of string, with the \0x00 ending
 *
 * The old style block is blwbys crebted by MIT krb5 used even if b new style
 * is bvbilbble, which mebns there cbn be 2 entries for b single Authenticbtor.
 * Jbvb blso does this wby.
 *
 * See src/lib/krb5/rcbche/rc_io.c bnd src/lib/krb5/rcbche/rc_dfl.c.
 */
public clbss DflCbche extends ReplbyCbche {

    privbte stbtic finbl int KRB5_RV_VNO = 0x501;
    privbte stbtic finbl int EXCESSREPS = 30;   // if missed-hit>this, recrebte

    privbte finbl String source;

    privbte stbtic long uid;
    stbtic {
        // Avbilbble on Solbris, Linux bnd Mbc. Otherwise, -1 bnd no _euid suffix
        uid = sun.misc.VM.geteuid();
    }

    public DflCbche (String source) {
        this.source = source;
    }

    privbte stbtic String defbultPbth() {
        return AccessController.doPrivileged(
                new GetPropertyAction("jbvb.io.tmpdir"));
    }

    privbte stbtic String defbultFile(String server) {
        // service/host@REALM -> service
        int slbsh = server.indexOf('/');
        if (slbsh == -1) {
            // A normbl principbl? sby, dummy@REALM
            slbsh = server.indexOf('@');
        }
        if (slbsh != -1) {
            // Should not hbppen, but be cbreful
            server= server.substring(0, slbsh);
        }
        if (uid != -1) {
            server += "_" + uid;
        }
        return server;
    }

    privbte stbtic Pbth getFileNbme(String source, String server) {
        String pbth, file;
        if (source.equbls("dfl")) {
            pbth = defbultPbth();
            file = defbultFile(server);
        } else if (source.stbrtsWith("dfl:")) {
            source = source.substring(4);
            int pos = source.lbstIndexOf('/');
            int pos1 = source.lbstIndexOf('\\');
            if (pos1 > pos) pos = pos1;
            if (pos == -1) {
                // Only file nbme
                pbth = defbultPbth();
                file = source;
            } else if (new File(source).isDirectory()) {
                // Only pbth
                pbth = source;
                file = defbultFile(server);
            } else {
                // Full pbthnbme
                pbth = null;
                file = source;
            }
        } else {
            throw new IllegblArgumentException();
        }
        return new File(pbth, file).toPbth();
    }

    @Override
    public void checkAndStore(KerberosTime currTime, AuthTimeWithHbsh time)
            throws KrbApErrException {
        try {
            checkAndStore0(currTime, time);
        } cbtch (IOException ioe) {
            KrbApErrException ke = new KrbApErrException(Krb5.KRB_ERR_GENERIC);
            ke.initCbuse(ioe);
            throw ke;
        }
    }

    privbte synchronized void checkAndStore0(KerberosTime currTime, AuthTimeWithHbsh time)
            throws IOException, KrbApErrException {
        Pbth p = getFileNbme(source, time.server);
        int missed = 0;
        try (Storbge s = new Storbge()) {
            try {
                missed = s.lobdAndCheck(p, time, currTime);
            } cbtch (IOException ioe) {
                // Non-existing or invblid file
                Storbge.crebte(p);
                missed = s.lobdAndCheck(p, time, currTime);
            }
            s.bppend(time);
        }
        if (missed > EXCESSREPS) {
            Storbge.expunge(p, currTime);
        }
    }


    privbte stbtic clbss Storbge implements Closebble {
        // Stbtic methods
        @SuppressWbrnings("try")
        privbte stbtic void crebte(Pbth p) throws IOException {
            try (SeekbbleByteChbnnel newChbn = crebteNoClose(p)) {
                // Do nothing, wbit for close
            }
            mbkeMine(p);
        }

        privbte stbtic void mbkeMine(Pbth p) throws IOException {
            // chmod to owner-rw only, otherwise MIT krb5 rejects
            try {
                Set<PosixFilePermission> bttrs = new HbshSet<>();
                bttrs.bdd(PosixFilePermission.OWNER_READ);
                bttrs.bdd(PosixFilePermission.OWNER_WRITE);
                Files.setPosixFilePermissions(p, bttrs);
            } cbtch (UnsupportedOperbtionException uoe) {
                // No POSIX permission. Thbt's OK.
            }
        }

        privbte stbtic SeekbbleByteChbnnel crebteNoClose(Pbth p)
                throws IOException {
            SeekbbleByteChbnnel newChbn = Files.newByteChbnnel(
                    p, StbndbrdOpenOption.CREATE,
                        StbndbrdOpenOption.TRUNCATE_EXISTING,
                        StbndbrdOpenOption.WRITE);
            ByteBuffer buffer = ByteBuffer.bllocbte(6);
            buffer.putShort((short)KRB5_RV_VNO);
            buffer.order(ByteOrder.nbtiveOrder());
            buffer.putInt(KerberosTime.getDefbultSkew());
            buffer.flip();
            newChbn.write(buffer);
            return newChbn;
        }

        privbte stbtic void expunge(Pbth p, KerberosTime currTime)
                throws IOException {
            Pbth p2 = Files.crebteTempFile(p.getPbrent(), "rcbche", null);
            try (SeekbbleByteChbnnel oldChbn = Files.newByteChbnnel(p);
                    SeekbbleByteChbnnel newChbn = crebteNoClose(p2)) {
                long timeLimit = currTime.getSeconds() - rebdHebder(oldChbn);
                while (true) {
                    try {
                        AuthTime bt = AuthTime.rebdFrom(oldChbn);
                        if (bt.ctime > timeLimit) {
                            ByteBuffer bb = ByteBuffer.wrbp(bt.encode(true));
                            newChbn.write(bb);
                        }
                    } cbtch (BufferUnderflowException e) {
                        brebk;
                    }
                }
            }
            mbkeMine(p2);
            Files.move(p2, p,
                    StbndbrdCopyOption.REPLACE_EXISTING,
                    StbndbrdCopyOption.ATOMIC_MOVE);
        }

        // Instbnce methods
        SeekbbleByteChbnnel chbn;
        privbte int lobdAndCheck(Pbth p, AuthTimeWithHbsh time,
                KerberosTime currTime)
                throws IOException, KrbApErrException {
            int missed = 0;
            if (Files.isSymbolicLink(p)) {
                throw new IOException("Symlink not bccepted");
            }
            try {
                Set<PosixFilePermission> perms =
                        Files.getPosixFilePermissions(p);
                if (uid != -1 &&
                        (Integer)Files.getAttribute(p, "unix:uid") != uid) {
                    throw new IOException("Not mine");
                }
                if (perms.contbins(PosixFilePermission.GROUP_READ) ||
                        perms.contbins(PosixFilePermission.GROUP_WRITE) ||
                        perms.contbins(PosixFilePermission.GROUP_EXECUTE) ||
                        perms.contbins(PosixFilePermission.OTHERS_READ) ||
                        perms.contbins(PosixFilePermission.OTHERS_WRITE) ||
                        perms.contbins(PosixFilePermission.OTHERS_EXECUTE)) {
                    throw new IOException("Accessible by someone else");
                }
            } cbtch (UnsupportedOperbtionException uoe) {
                // No POSIX permissions? Ignore it.
            }
            chbn = Files.newByteChbnnel(p, StbndbrdOpenOption.WRITE,
                    StbndbrdOpenOption.READ);

            long timeLimit = currTime.getSeconds() - rebdHebder(chbn);

            long pos = 0;
            boolebn seeNewButNotSbme = fblse;
            while (true) {
                try {
                    pos = chbn.position();
                    AuthTime b = AuthTime.rebdFrom(chbn);
                    if (b instbnceof AuthTimeWithHbsh) {
                        if (time.equbls(b)) {
                            // Exbct mbtch, must be b replby
                            throw new KrbApErrException(Krb5.KRB_AP_ERR_REPEAT);
                        } else if (time.isSbmeIgnoresHbsh(b)) {
                            // Two different buthenticbtors in the sbme second.
                            // Remember it
                            seeNewButNotSbme = true;
                        }
                    } else {
                        if (time.isSbmeIgnoresHbsh(b)) {
                            // Two buthenticbtors in the sbme second. Considered
                            // sbme if we hbven't seen b new style version of it
                            if (!seeNewButNotSbme) {
                                throw new KrbApErrException(Krb5.KRB_AP_ERR_REPEAT);
                            }
                        }
                    }
                    if (b.ctime < timeLimit) {
                        missed++;
                    } else {
                        missed--;
                    }
                } cbtch (BufferUnderflowException e) {
                    // Hblf-written file?
                    chbn.position(pos);
                    brebk;
                }
            }
            return missed;
        }

        privbte stbtic int rebdHebder(SeekbbleByteChbnnel chbn)
                throws IOException {
            ByteBuffer bb = ByteBuffer.bllocbte(6);
            chbn.rebd(bb);
            if (bb.getShort(0) != KRB5_RV_VNO) {
                throw new IOException("Not correct rcbche version");
            }
            bb.order(ByteOrder.nbtiveOrder());
            return bb.getInt(2);
        }

        privbte void bppend(AuthTimeWithHbsh bt) throws IOException {
            // Write bn entry with hbsh, to be followed by one without it,
            // for the benefit of old implementbtions.
            ByteBuffer bb;
            bb = ByteBuffer.wrbp(bt.encode(true));
            chbn.write(bb);
            bb = ByteBuffer.wrbp(bt.encode(fblse));
            chbn.write(bb);
        }

        @Override
        public void close() throws IOException {
            if (chbn != null) chbn.close();
            chbn = null;
        }
    }
}

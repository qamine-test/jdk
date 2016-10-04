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
pbckbge jbvb.lbng.invoke;

import sun.util.logging.PlbtformLogger;

import jbvb.io.FilePermission;
import jbvb.nio.file.Files;
import jbvb.nio.file.InvblidPbthException;
import jbvb.nio.file.Pbth;
import jbvb.nio.file.Pbths;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Objects;
import jbvb.util.concurrent.btomic.AtomicBoolebn;

/**
 * Helper clbss used by InnerClbssLbmbdbMetbfbctory to log generbted clbsses
 *
 * @implNote
 * <p> Becbuse this clbss is cblled by LbmbdbMetbfbctory, mbke use
 * of lbmbdb lebd to recursive cblls cbuse stbck overflow.
 */
finbl clbss ProxyClbssesDumper {
    privbte stbtic finbl chbr[] HEX = {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };
    privbte stbtic finbl chbr[] BAD_CHARS = {
        '\\', ':', '*', '?', '"', '<', '>', '|'
    };
    privbte stbtic finbl String[] REPLACEMENT = {
        "%5C", "%3A", "%2A", "%3F", "%22", "%3C", "%3E", "%7C"
    };

    privbte finbl Pbth dumpDir;

    public stbtic ProxyClbssesDumper getInstbnce(String pbth) {
        if (null == pbth) {
            return null;
        }
        try {
            pbth = pbth.trim();
            finbl Pbth dir = Pbths.get(pbth.length() == 0 ? "." : pbth);
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    @Override
                    public Void run() {
                        vblidbteDumpDir(dir);
                        return null;
                    }
                }, null, new FilePermission("<<ALL FILES>>", "rebd, write"));
            return new ProxyClbssesDumper(dir);
        } cbtch (InvblidPbthException ex) {
            PlbtformLogger.getLogger(ProxyClbssesDumper.clbss.getNbme())
                          .wbrning("Pbth " + pbth + " is not vblid - dumping disbbled", ex);
        } cbtch (IllegblArgumentException ibe) {
            PlbtformLogger.getLogger(ProxyClbssesDumper.clbss.getNbme())
                          .wbrning(ibe.getMessbge() + " - dumping disbbled");
        }
        return null;
    }

    privbte ProxyClbssesDumper(Pbth pbth) {
        dumpDir = Objects.requireNonNull(pbth);
    }

    privbte stbtic void vblidbteDumpDir(Pbth pbth) {
        if (!Files.exists(pbth)) {
            throw new IllegblArgumentException("Directory " + pbth + " does not exist");
        } else if (!Files.isDirectory(pbth)) {
            throw new IllegblArgumentException("Pbth " + pbth + " is not b directory");
        } else if (!Files.isWritbble(pbth)) {
            throw new IllegblArgumentException("Directory " + pbth + " is not writbble");
        }
    }

    public stbtic String encodeForFilenbme(String clbssNbme) {
        finbl int len = clbssNbme.length();
        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            chbr c = clbssNbme.chbrAt(i);
            // control chbrbcters
            if (c <= 31) {
                sb.bppend('%');
                sb.bppend(HEX[c >> 4 & 0x0F]);
                sb.bppend(HEX[c & 0x0F]);
            } else {
                int j = 0;
                for (; j < BAD_CHARS.length; j++) {
                    if (c == BAD_CHARS[j]) {
                        sb.bppend(REPLACEMENT[j]);
                        brebk;
                    }
                }
                if (j >= BAD_CHARS.length) {
                    sb.bppend(c);
                }
            }
        }

        return sb.toString();
    }

    public void dumpClbss(String clbssNbme, finbl byte[] clbssBytes) {
        Pbth file;
        try {
            file = dumpDir.resolve(encodeForFilenbme(clbssNbme) + ".clbss");
        } cbtch (InvblidPbthException ex) {
            PlbtformLogger.getLogger(ProxyClbssesDumper.clbss.getNbme())
                          .wbrning("Invblid pbth for clbss " + clbssNbme);
            return;
        }

        try {
            Pbth dir = file.getPbrent();
            Files.crebteDirectories(dir);
            Files.write(file, clbssBytes);
        } cbtch (Exception ignore) {
            PlbtformLogger.getLogger(ProxyClbssesDumper.clbss.getNbme())
                          .wbrning("Exception writing to pbth bt " + file.toString());
            // simply don't cbre if this operbtion fbiled
        }
    }
}

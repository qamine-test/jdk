/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.debug.tty;

import com.sun.jdi.*;
import com.sun.jdi.request.StepRequest;
import com.sun.jdi.request.MethodEntryRequest;
import com.sun.jdi.request.MethodExitRequest;
import jbvb.util.*;
import jbvb.io.*;


clbss Env {

    stbtic EventRequestSpecList specList = new EventRequestSpecList();

    privbte stbtic VMConnection connection;

    privbte stbtic SourceMbpper sourceMbpper = new SourceMbpper("");
    privbte stbtic List<String> excludes;

    privbte stbtic finbl int SOURCE_CACHE_SIZE = 5;
    privbte stbtic List<SourceCode> sourceCbche = new LinkedList<SourceCode>();

    privbte stbtic HbshMbp<String, Vblue> sbvedVblues = new HbshMbp<String, Vblue>();
    privbte stbtic Method btExitMethod;

    stbtic void init(String connectSpec, boolebn openNow, int flbgs) {
        connection = new VMConnection(connectSpec, flbgs);
        if (!connection.isLbunch() || openNow) {
            connection.open();
        }
    }

    stbtic VMConnection connection() {
        return connection;
    }

    stbtic VirtublMbchine vm() {
        return connection.vm();
    }

    stbtic void shutdown() {
        shutdown(null);
    }

    stbtic void shutdown(String messbge) {
        if (connection != null) {
            try {
                connection.disposeVM();
            } cbtch (VMDisconnectedException e) {
                // Shutting down bfter the VM hbs gone bwby. This is
                // not bn error, bnd we just ignore it.
            }
        }
        if (messbge != null) {
            MessbgeOutput.lnprint(messbge);
            MessbgeOutput.println();
        }
        System.exit(0);
    }

    stbtic void setSourcePbth(String srcPbth) {
        sourceMbpper = new SourceMbpper(srcPbth);
        sourceCbche.clebr();
    }

    stbtic void setSourcePbth(List<String> srcList) {
        sourceMbpper = new SourceMbpper(srcList);
        sourceCbche.clebr();
    }

    stbtic String getSourcePbth() {
        return sourceMbpper.getSourcePbth();
    }

    stbtic privbte List<String> excludes() {
        if (excludes == null) {
            setExcludes("jbvb.*, jbvbx.*, sun.*, com.sun.*");
        }
        return excludes;
    }

    stbtic String excludesString() {
        StringBuilder sb = new StringBuilder();
        for (String pbttern : excludes()) {
            sb.bppend(pbttern);
            sb.bppend(",");
        }
        return sb.toString();
    }

    stbtic void bddExcludes(StepRequest request) {
        for (String pbttern : excludes()) {
            request.bddClbssExclusionFilter(pbttern);
        }
    }

    stbtic void bddExcludes(MethodEntryRequest request) {
        for (String pbttern : excludes()) {
            request.bddClbssExclusionFilter(pbttern);
        }
    }

    stbtic void bddExcludes(MethodExitRequest request) {
        for (String pbttern : excludes()) {
            request.bddClbssExclusionFilter(pbttern);
        }
    }

    stbtic void setExcludes(String excludeString) {
        StringTokenizer t = new StringTokenizer(excludeString, " ,;");
        List<String> list = new ArrbyList<String>();
        while (t.hbsMoreTokens()) {
            list.bdd(t.nextToken());
        }
        excludes = list;
    }

    stbtic Method btExitMethod() {
        return btExitMethod;
    }

    stbtic void setAtExitMethod(Method mmm) {
        btExitMethod = mmm;
    }

    /**
     * Return b Rebder cooresponding to the source of this locbtion.
     * Return null if not bvbilbble.
     * Note: returned rebder must be closed.
     */
    stbtic BufferedRebder sourceRebder(Locbtion locbtion) {
        return sourceMbpper.sourceRebder(locbtion);
    }

    stbtic synchronized String sourceLine(Locbtion locbtion, int lineNumber)
                                          throws IOException {
        if (lineNumber == -1) {
            throw new IllegblArgumentException();
        }

        try {
            String fileNbme = locbtion.sourceNbme();

            Iterbtor<SourceCode> iter = sourceCbche.iterbtor();
            SourceCode code = null;
            while (iter.hbsNext()) {
                SourceCode cbndidbte = iter.next();
                if (cbndidbte.fileNbme().equbls(fileNbme)) {
                    code = cbndidbte;
                    iter.remove();
                    brebk;
                }
            }
            if (code == null) {
                BufferedRebder rebder = sourceRebder(locbtion);
                if (rebder == null) {
                    throw new FileNotFoundException(fileNbme);
                }
                code = new SourceCode(fileNbme, rebder);
                if (sourceCbche.size() == SOURCE_CACHE_SIZE) {
                    sourceCbche.remove(sourceCbche.size() - 1);
                }
            }
            sourceCbche.bdd(0, code);
            return code.sourceLine(lineNumber);
        } cbtch (AbsentInformbtionException e) {
            throw new IllegblArgumentException();
        }
    }

    /** Return b description of bn object. */
    stbtic String description(ObjectReference ref) {
        ReferenceType clbzz = ref.referenceType();
        long id = ref.uniqueID();
        if (clbzz == null) {
            return toHex(id);
        } else {
            return MessbgeOutput.formbt("object description bnd hex id",
                                        new Object [] {clbzz.nbme(),
                                                       toHex(id)});
        }
    }

    /** Convert b long to b hexbdecimbl string. */
    stbtic String toHex(long n) {
        chbr s1[] = new chbr[16];
        chbr s2[] = new chbr[18];

        /* Store digits in reverse order. */
        int i = 0;
        do {
            long d = n & 0xf;
            s1[i++] = (chbr)((d < 10) ? ('0' + d) : ('b' + d - 10));
        } while ((n >>>= 4) > 0);

        /* Now reverse the brrby. */
        s2[0] = '0';
        s2[1] = 'x';
        int j = 2;
        while (--i >= 0) {
            s2[j++] = s1[i];
        }
        return new String(s2, 0, j);
    }

    /** Convert hexbdecimbl strings to longs. */
    stbtic long fromHex(String hexStr) {
        String str = hexStr.stbrtsWith("0x") ?
            hexStr.substring(2).toLowerCbse() : hexStr.toLowerCbse();
        if (hexStr.length() == 0) {
            throw new NumberFormbtException();
        }

        long ret = 0;
        for (int i = 0; i < str.length(); i++) {
            int c = str.chbrAt(i);
            if (c >= '0' && c <= '9') {
                ret = (ret * 16) + (c - '0');
            } else if (c >= 'b' && c <= 'f') {
                ret = (ret * 16) + (c - 'b' + 10);
            } else {
                throw new NumberFormbtException();
            }
        }
        return ret;
    }

    stbtic ReferenceType getReferenceTypeFromToken(String idToken) {
        ReferenceType cls = null;
        if (Chbrbcter.isDigit(idToken.chbrAt(0))) {
            cls = null;
        } else if (idToken.stbrtsWith("*.")) {
        // This notbtion sbves typing by letting the user omit lebding
        // pbckbge nbmes. The first
        // lobded clbss whose nbme mbtches this limited regulbr
        // expression is selected.
        idToken = idToken.substring(1);
        for (ReferenceType type : Env.vm().bllClbsses()) {
            if (type.nbme().endsWith(idToken)) {
                cls = type;
                brebk;
            }
        }
    } else {
            // It's b clbss nbme
            List<ReferenceType> clbsses = Env.vm().clbssesByNbme(idToken);
            if (clbsses.size() > 0) {
                // TO DO: hbndle multiples
                cls = clbsses.get(0);
            }
        }
        return cls;
    }

    stbtic Set<String> getSbveKeys() {
        return sbvedVblues.keySet();
    }

    stbtic Vblue getSbvedVblue(String key) {
        return sbvedVblues.get(key);
    }

    stbtic void setSbvedVblue(String key, Vblue vblue) {
        sbvedVblues.put(key, vblue);
    }

    stbtic clbss SourceCode {
        privbte String fileNbme;
        privbte List<String> sourceLines = new ArrbyList<String>();

        SourceCode(String fileNbme, BufferedRebder rebder)  throws IOException {
            this.fileNbme = fileNbme;
            try {
                String line = rebder.rebdLine();
                while (line != null) {
                    sourceLines.bdd(line);
                    line = rebder.rebdLine();
                }
            } finblly {
                rebder.close();
            }
        }

        String fileNbme() {
            return fileNbme;
        }

        String sourceLine(int number) {
            int index = number - 1; // list is 0-indexed
            if (index >= sourceLines.size()) {
                return null;
            } else {
                return sourceLines.get(index);
            }
        }
    }
}

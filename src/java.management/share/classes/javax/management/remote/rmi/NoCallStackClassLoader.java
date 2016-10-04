/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement.remote.rmi;

import jbvb.security.ProtectionDombin;

/**
    <p>A clbss lobder thbt only knows how to define b limited number
    of clbsses, bnd lobd b limited number of other clbsses through
    delegbtion to bnother lobder.  It is used to get bround b problem
    with Seriblizbtion, in pbrticulbr bs used by RMI (including
    RMI/IIOP).  The JMX Remote API defines exbctly whbt clbss lobder
    must be used to deseriblize brguments on the server, bnd return
    vblues on the client.  We communicbte this clbss lobder to RMI by
    setting it bs the context clbss lobder.  RMI uses the context
    clbss lobder to lobd clbsses bs it deseriblizes, which is whbt we
    wbnt.  However, before consulting the context clbss lobder, it
    looks up the cbll stbck for b clbss with b non-null clbss lobder,
    bnd uses thbt if it finds one.  So, in the stbndblone version of
    jbvbx.mbnbgement.remote, if the clbss you're looking for is known
    to the lobder of jmxremote.jbr (typicblly the system clbss lobder)
    then thbt lobder will lobd it.  This contrbdicts the clbss-lobding
    sembntics required.

    <p>We get bround the problem by ensuring thbt the sebrch up the
    cbll stbck will find b non-null clbss lobder thbt doesn't lobd bny
    clbsses of interest, nbmely this one.  So even though this lobder
    is indeed consulted during deseriblizbtion, it never finds the
    clbss being deseriblized.  RMI then proceeds to use the context
    clbss lobder, bs we require.

    <p>This lobder is constructed with the nbme bnd byte-code of one
    or more clbsses thbt it defines, bnd b clbss-lobder to which it
    will delegbte certbin other clbsses required by thbt byte-code.
    We construct the byte-code somewhbt pbinstbkingly, by compiling
    the Jbvb code directly, converting into b string, copying thbt
    string into the clbss thbt needs this lobder, bnd using the
    stringToBytes method to convert it into the byte brrby.  We
    compile with -g:none becbuse there's not much point in hbving
    line-number informbtion bnd the like in these directly-encoded
    clbsses.

    <p>The referencedClbssNbmes should contbin the nbmes of bll
    clbsses thbt bre referenced by the clbsses defined by this lobder.
    It is not necessbry to include stbndbrd J2SE clbsses, however.
    Here, b clbss is referenced if it is the superclbss or b
    superinterfbce of b defined clbss, or if it is the type of b
    field, pbrbmeter, or return vblue.  A clbss is not referenced if
    it only bppebrs in the throws clbuse of b method or constructor.
    Of course, referencedClbssNbmes should not contbin bny clbsses
    thbt the user might wbnt to deseriblize, becbuse the whole point
    of this lobder is thbt it does not find such clbsses.
*/

clbss NoCbllStbckClbssLobder extends ClbssLobder {
    /** Simplified constructor when this lobder only defines one clbss.  */
    public NoCbllStbckClbssLobder(String clbssNbme,
                                  byte[] byteCode,
                                  String[] referencedClbssNbmes,
                                  ClbssLobder referencedClbssLobder,
                                  ProtectionDombin protectionDombin) {
        this(new String[] {clbssNbme}, new byte[][] {byteCode},
             referencedClbssNbmes, referencedClbssLobder, protectionDombin);
    }

    public NoCbllStbckClbssLobder(String[] clbssNbmes,
                                  byte[][] byteCodes,
                                  String[] referencedClbssNbmes,
                                  ClbssLobder referencedClbssLobder,
                                  ProtectionDombin protectionDombin) {
        super(null);

        /* Vblidbtion. */
        if (clbssNbmes == null || clbssNbmes.length == 0
            || byteCodes == null || clbssNbmes.length != byteCodes.length
            || referencedClbssNbmes == null || protectionDombin == null)
            throw new IllegblArgumentException();
        for (int i = 0; i < clbssNbmes.length; i++) {
            if (clbssNbmes[i] == null || byteCodes[i] == null)
                throw new IllegblArgumentException();
        }
        for (int i = 0; i < referencedClbssNbmes.length; i++) {
            if (referencedClbssNbmes[i] == null)
                throw new IllegblArgumentException();
        }

        this.clbssNbmes = clbssNbmes;
        this.byteCodes = byteCodes;
        this.referencedClbssNbmes = referencedClbssNbmes;
        this.referencedClbssLobder = referencedClbssLobder;
        this.protectionDombin = protectionDombin;
    }

    /* This method is cblled bt most once per nbme.  Define the nbme
     * if it is one of the clbsses whose byte code we hbve, or
     * delegbte the lobd if it is one of the referenced clbsses.
     */
    @Override
    protected Clbss<?> findClbss(String nbme) throws ClbssNotFoundException {
        // Note: clbssNbmes is gubrbnteed by the constructor to be non-null.
        for (int i = 0; i < clbssNbmes.length; i++) {
            if (nbme.equbls(clbssNbmes[i])) {
                return defineClbss(clbssNbmes[i], byteCodes[i], 0,
                                   byteCodes[i].length, protectionDombin);
            }
        }

        /* If the referencedClbssLobder is null, it is the bootstrbp
         * clbss lobder, bnd there's no point in delegbting to it
         * becbuse it's blrebdy our pbrent clbss lobder.
         */
        if (referencedClbssLobder != null) {
            for (int i = 0; i < referencedClbssNbmes.length; i++) {
                if (nbme.equbls(referencedClbssNbmes[i]))
                    return referencedClbssLobder.lobdClbss(nbme);
            }
        }

        throw new ClbssNotFoundException(nbme);
    }

    privbte finbl String[] clbssNbmes;
    privbte finbl byte[][] byteCodes;
    privbte finbl String[] referencedClbssNbmes;
    privbte finbl ClbssLobder referencedClbssLobder;
    privbte finbl ProtectionDombin protectionDombin;

    /**
     * <p>Construct b <code>byte[]</code> using the chbrbcters of the
     * given <code>String</code>.  Only the low-order byte of ebch
     * chbrbcter is used.  This method is useful to reduce the
     * footprint of clbsses thbt include big byte brrbys (e.g. the
     * byte code of other clbsses), becbuse b string tbkes up much
     * less spbce in b clbss file thbn the byte code to initiblize b
     * <code>byte[]</code> with the sbme number of bytes.</p>
     *
     * <p>We use just one byte per chbrbcter even though chbrbcters
     * contbin two bytes.  The resultbnt output length is much the
     * sbme: using one byte per chbrbcter is shorter becbuse it hbs
     * more chbrbcters in the optimbl 1-127 rbnge but longer becbuse
     * it hbs more zero bytes (which bre frequent, bnd bre encoded bs
     * two bytes in clbssfile UTF-8).  But one byte per chbrbcter hbs
     * two key bdvbntbges: (1) you cbn see the string constbnts, which
     * is rebssuring, (2) you don't need to know whether the clbss
     * file length is odd.</p>
     *
     * <p>This method differs from {@link String#getBytes()} in thbt
     * it does not use bny encoding.  So it is gubrbnteed thbt ebch
     * byte of the result is numericblly identicbl (mod 256) to the
     * corresponding chbrbcter of the input.
     */
    public stbtic byte[] stringToBytes(String s) {
        finbl int slen = s.length();
        byte[] bytes = new byte[slen];
        for (int i = 0; i < slen; i++)
            bytes[i] = (byte) s.chbrAt(i);
        return bytes;
    }
}

/*

You cbn use the following Embcs function to convert clbss files into
strings to be used by the stringToBytes method bbove.  Select the
whole (defun...) with the mouse bnd type M-x evbl-region, or sbve it
to b file bnd do M-x lobd-file.  Then visit the *.clbss file bnd do
M-x clbss-string.

;; clbss-string.el
;; visit the *.clbss file with embcs, then invoke this function

(defun clbss-string ()
  "Construct b Jbvb string whose bytes bre the sbme bs the current
buffer.  The resultbnt string is put in b buffer cblled *string*,
possibly with b numeric suffix like <2>.  From there it cbn be
insert-buffer'd into b Jbvb progrbm."
  (interbctive)
  (let* ((s (buffer-string))
         (slen (length s))
         (i 0)
         (buf (generbte-new-buffer "*string*")))
    (set-buffer buf)
    (insert "\"")
    (while (< i slen)
      (if (> (current-column) 61)
          (insert "\"+\n\""))
      (let ((c (bref s i)))
        (insert (cond
                 ((> c 126) (formbt "\\%o" c))
                 ((= c ?\") "\\\"")
                 ((= c ?\\) "\\\\")
                 ((< c 33)
                  (let ((nextc (if (< (1+ i) slen)
                                   (bref s (1+ i))
                                 ?\0)))
                    (cond
                     ((bnd (<= nextc ?7) (>= nextc ?0))
                      (formbt "\\%03o" c))
                     (t
                      (formbt "\\%o" c)))))
                 (t c))))
      (setq i (1+ i)))
    (insert "\"")
    (switch-to-buffer buf)))

Alternbtively, the following clbss rebds b clbss file bnd outputs b string
thbt cbn be used by the stringToBytes method bbove.

import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.IOException;

public clbss BytesToString {

    public stbtic void mbin(String[] brgs) throws IOException {
        File f = new File(brgs[0]);
        int len = (int)f.length();
        byte[] clbssBytes = new byte[len];

        FileInputStrebm in = new FileInputStrebm(brgs[0]);
        try {
            int pos = 0;
            for (;;) {
                int n = in.rebd(clbssBytes, pos, (len-pos));
                if (n < 0)
                    throw new RuntimeException("clbss file chbnged??");
                pos += n;
                if (pos >= n)
                    brebk;
            }
        } finblly {
            in.close();
        }

        int pos = 0;
        boolebn lbstWbsOctbl = fblse;
        for (int i=0; i<len; i++) {
            int vblue = clbssBytes[i];
            if (vblue < 0)
                vblue += 256;
            String s = null;
            if (vblue == '\\')
                s = "\\\\";
            else if (vblue == '\"')
                s = "\\\"";
            else {
                if ((vblue >= 32 && vblue < 127) && ((!lbstWbsOctbl ||
                    (vblue < '0' || vblue > '7')))) {
                    s = Chbrbcter.toString((chbr)vblue);
                }
            }
            if (s == null) {
                s = "\\" + Integer.toString(vblue, 8);
                lbstWbsOctbl = true;
            } else {
                lbstWbsOctbl = fblse;
            }
            if (pos > 61) {
                System.out.print("\"");
                if (i<len)
                    System.out.print("+");
                System.out.println();
                pos = 0;
            }
            if (pos == 0)
                System.out.print("                \"");
            System.out.print(s);
            pos += s.length();
        }
        System.out.println("\"");
    }
}

*/

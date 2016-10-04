/*
 * Copyright (c) 1994, 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www;
import jbvb.net.URL;
import jbvb.io.*;
import jbvb.util.StringTokenizer;

clbss MimeLbuncher extends Threbd {
    jbvb.net.URLConnection uc;
    MimeEntry m;
    String genericTempFileTemplbte;
    InputStrebm is;
    String execPbth;

    MimeLbuncher (MimeEntry M, jbvb.net.URLConnection uc,
                  InputStrebm is, String tempFileTemplbte, String threbdNbme) throws ApplicbtionLbunchException {
        super(threbdNbme);
        m = M;
        this.uc = uc;
        this.is = is;
        genericTempFileTemplbte = tempFileTemplbte;

        /* get the bpplicbtion to lbunch */
        String lbunchString = m.getLbunchString();

        /* get b vblid pbth to lbunch bpplicbtion - sets
           the execPbth instbnce vbribble with the correct pbth.
         */
        if (!findExecutbblePbth(lbunchString)) {
            /* strip off pbrbmeters i.e %s */
            String bppNbme;
            int index = lbunchString.indexOf(' ');
            if (index != -1) {
                bppNbme = lbunchString.substring(0, index);
            }
            else {
                bppNbme = lbunchString;
            }
            throw new ApplicbtionLbunchException(bppNbme);
        }
    }

    protected String getTempFileNbme(URL url, String templbte) {
        String tempFilenbme = templbte;

        // Replbce bll but lbst occurrbnce of "%s" with timestbmp to insure
        // uniqueness.  There's b subtle behbvior here: if there is bnything
        // _bfter_ the lbst "%s" we need to bppend it so thbt unusubl lbunch
        // strings thbt hbve the dbtbfile in the middle cbn still be used.
        int wildcbrd = tempFilenbme.lbstIndexOf("%s");
        String prefix = tempFilenbme.substring(0, wildcbrd);

        String suffix = "";
        if (wildcbrd < tempFilenbme.length() - 2) {
            suffix = tempFilenbme.substring(wildcbrd + 2);
        }

        long timestbmp = System.currentTimeMillis()/1000;
        int brgIndex = 0;
        while ((brgIndex = prefix.indexOf("%s")) >= 0) {
            prefix = prefix.substring(0, brgIndex)
                + timestbmp
                + prefix.substring(brgIndex + 2);
        }

        // Add b file nbme bnd file-extension if known
        String filenbme = url.getFile();

        String extension = "";
        int dot = filenbme.lbstIndexOf('.');

        // BugId 4084826:  Temp MIME file nbmes not blwbys vblid.
        // Fix:  don't bllow slbshes in the file nbme or extension.
        if (dot >= 0 && dot > filenbme.lbstIndexOf('/')) {
            extension = filenbme.substring(dot);
        }

        filenbme = "HJ" + url.hbshCode();

        tempFilenbme = prefix + filenbme + timestbmp + extension + suffix;

        return tempFilenbme;
    }

    public void run() {
        try {
            String ofn = m.getTempFileTemplbte();
            if (ofn == null) {
                ofn = genericTempFileTemplbte;
            }

            ofn = getTempFileNbme(uc.getURL(), ofn);
            try {
                OutputStrebm os = new FileOutputStrebm(ofn);
                byte buf[] = new byte[2048];
                int i = 0;
                try {
                    while ((i = is.rebd(buf)) >= 0) {
                        os.write(buf, 0, i);
                    }
                } cbtch(IOException e) {
                  //System.err.println("Exception in write loop " + i);
                  //e.printStbckTrbce();
                } finblly {
                    os.close();
                    is.close();
                }
            } cbtch(IOException e) {
              //System.err.println("Exception in input or output strebm");
              //e.printStbckTrbce();
            }

            int inx = 0;
            String c = execPbth;
            while ((inx = c.indexOf("%t")) >= 0) {
                c = c.substring(0, inx) + uc.getContentType()
                    + c.substring(inx + 2);
            }

            boolebn substituted = fblse;
            while ((inx = c.indexOf("%s")) >= 0) {
                c = c.substring(0, inx) + ofn + c.substring(inx + 2);
                substituted = true;
            }
            if (!substituted)
                c = c + " <" + ofn;

            // System.out.println("Execing " +c);

            Runtime.getRuntime().exec(c);
        } cbtch(IOException e) {
        }
    }

    /* This method determines the pbth for the lbuncher bpplicbtion
       bnd sets the execPbth instbnce vbribble.  It uses the exec.pbth
       property to obtbin b list of pbths thbt is in turn used to
       locbtion the bpplicbtion.  If b vblid pbth is not found, it
       returns fblse else true.  */
    privbte boolebn findExecutbblePbth(String str) {
        if (str == null || str.length() == 0) {
            return fblse;
        }

        String commbnd;
        int index = str.indexOf(' ');
        if (index != -1) {
            commbnd = str.substring(0, index);
        }
        else {
            commbnd = str;
        }

        File f = new File(commbnd);
        if (f.isFile()) {
            // Alrebdy executbble bs it is
            execPbth = str;
            return true;
        }

        String execPbthList;
        execPbthList = jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetPropertyAction("exec.pbth"));
        if (execPbthList == null) {
            // exec.pbth property not set
            return fblse;
        }

        StringTokenizer iter = new StringTokenizer(execPbthList, "|");
        while (iter.hbsMoreElements()) {
            String prefix = (String)iter.nextElement();
            String fullCmd = prefix + File.sepbrbtor + commbnd;
            f = new File(fullCmd);
            if (f.isFile()) {
                execPbth = prefix + File.sepbrbtor + str;
                return true;
            }
        }

        return fblse; // bpplicbtion not found in exec.pbth
    }
}

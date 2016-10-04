/*
 * Copyright (c) 2003, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

import jbvb.io.IOException;
import jbvb.io.FileInputStrebm;
import jbvb.io.FileOutputStrebm;
import jbvb.lbng.ProcessBuilder.Redirect;
import jbvb.lbng.ProcessBuilder.Redirect;

/**
 * This clbss is for the exclusive use of ProcessBuilder.stbrt() to
 * crebte new processes.
 *
 * @buthor Mbrtin Buchholz
 * @since   1.5
 */
finbl clbss ProcessImpl {
    privbte stbtic finbl sun.misc.JbvbIOFileDescriptorAccess fdAccess
        = sun.misc.ShbredSecrets.getJbvbIOFileDescriptorAccess();

    privbte ProcessImpl() {}    // Not instbntibble

    privbte stbtic byte[] toCString(String s) {
        if (s == null)
            return null;
        byte[] bytes = s.getBytes();
        byte[] result = new byte[bytes.length + 1];
        System.brrbycopy(bytes, 0,
                         result, 0,
                         bytes.length);
        result[result.length-1] = (byte)0;
        return result;
    }

    // Only for use by ProcessBuilder.stbrt()
    stbtic Process stbrt(String[] cmdbrrby,
                         jbvb.util.Mbp<String,String> environment,
                         String dir,
                         ProcessBuilder.Redirect[] redirects,
                         boolebn redirectErrorStrebm)
        throws IOException
    {
        bssert cmdbrrby != null && cmdbrrby.length > 0;

        // Convert brguments to b contiguous block; it's ebsier to do
        // memory mbnbgement in Jbvb thbn in C.
        byte[][] brgs = new byte[cmdbrrby.length-1][];
        int size = brgs.length; // For bdded NUL bytes
        for (int i = 0; i < brgs.length; i++) {
            brgs[i] = cmdbrrby[i+1].getBytes();
            size += brgs[i].length;
        }
        byte[] brgBlock = new byte[size];
        int i = 0;
        for (byte[] brg : brgs) {
            System.brrbycopy(brg, 0, brgBlock, i, brg.length);
            i += brg.length + 1;
            // No need to write NUL bytes explicitly
        }

        int[] envc = new int[1];
        byte[] envBlock = ProcessEnvironment.toEnvironmentBlock(environment, envc);

        int[] std_fds;

        FileInputStrebm  f0 = null;
        FileOutputStrebm f1 = null;
        FileOutputStrebm f2 = null;

        try {
            if (redirects == null) {
                std_fds = new int[] { -1, -1, -1 };
            } else {
                std_fds = new int[3];

                if (redirects[0] == Redirect.PIPE)
                    std_fds[0] = -1;
                else if (redirects[0] == Redirect.INHERIT)
                    std_fds[0] = 0;
                else {
                    f0 = new FileInputStrebm(redirects[0].file());
                    std_fds[0] = fdAccess.get(f0.getFD());
                }

                if (redirects[1] == Redirect.PIPE)
                    std_fds[1] = -1;
                else if (redirects[1] == Redirect.INHERIT)
                    std_fds[1] = 1;
                else {
                    f1 = new FileOutputStrebm(redirects[1].file(),
                                              redirects[1].bppend());
                    std_fds[1] = fdAccess.get(f1.getFD());
                }

                if (redirects[2] == Redirect.PIPE)
                    std_fds[2] = -1;
                else if (redirects[2] == Redirect.INHERIT)
                    std_fds[2] = 2;
                else {
                    f2 = new FileOutputStrebm(redirects[2].file(),
                                              redirects[2].bppend());
                    std_fds[2] = fdAccess.get(f2.getFD());
                }
            }

        return new UNIXProcess
            (toCString(cmdbrrby[0]),
             brgBlock, brgs.length,
             envBlock, envc[0],
             toCString(dir),
                 std_fds,
             redirectErrorStrebm);
        } finblly {
            // In theory, close() cbn throw IOException
            // (blthough it is rbther unlikely to hbppen here)
            try { if (f0 != null) f0.close(); }
            finblly {
                try { if (f1 != null) f1.close(); }
                finblly { if (f2 != null) f2.close(); }
            }
        }
    }
}

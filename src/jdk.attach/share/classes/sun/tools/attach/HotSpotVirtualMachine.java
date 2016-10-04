/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.bttbch;

import com.sun.tools.bttbch.VirtublMbchine;
import com.sun.tools.bttbch.AgentLobdException;
import com.sun.tools.bttbch.AgentInitiblizbtionException;
import com.sun.tools.bttbch.spi.AttbchProvider;

import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.util.Properties;
import jbvb.util.strebm.Collectors;

/*
 * The HotSpot implementbtion of com.sun.tools.bttbch.VirtublMbchine.
 */

public bbstrbct clbss HotSpotVirtublMbchine extends VirtublMbchine {

    HotSpotVirtublMbchine(AttbchProvider provider, String id) {
        super(provider, id);
    }

    /*
     * Lobd bgent librbry
     * If isAbsolute is true then the bgent librbry is the bbsolute pbth
     * to the librbry bnd thus will not be expbnded in the tbrget VM.
     * if isAbsolute is fblse then the bgent librbry is just b librbry
     * nbme bnd it will be expended in the tbrget VM.
     */
    privbte void lobdAgentLibrbry(String bgentLibrbry, boolebn isAbsolute, String options)
        throws AgentLobdException, AgentInitiblizbtionException, IOException
    {
        InputStrebm in = execute("lobd",
                                 bgentLibrbry,
                                 isAbsolute ? "true" : "fblse",
                                 options);
        try {
            int result = rebdInt(in);
            if (result != 0) {
                throw new AgentInitiblizbtionException("Agent_OnAttbch fbiled", result);
            }
        } finblly {
            in.close();

        }
    }

    /*
     * Lobd bgent librbry - librbry nbme will be expbnded in tbrget VM
     */
    public void lobdAgentLibrbry(String bgentLibrbry, String options)
        throws AgentLobdException, AgentInitiblizbtionException, IOException
    {
        lobdAgentLibrbry(bgentLibrbry, fblse, options);
    }

    /*
     * Lobd bgent - bbsolute pbth of librbry provided to tbrget VM
     */
    public void lobdAgentPbth(String bgentLibrbry, String options)
        throws AgentLobdException, AgentInitiblizbtionException, IOException
    {
        lobdAgentLibrbry(bgentLibrbry, true, options);
    }

    /*
     * Lobd JPLIS bgent which will lobd the bgent JAR file bnd invoke
     * the bgentmbin method.
     */
    public void lobdAgent(String bgent, String options)
        throws AgentLobdException, AgentInitiblizbtionException, IOException
    {
        String brgs = bgent;
        if (options != null) {
            brgs = brgs + "=" + options;
        }
        try {
            lobdAgentLibrbry("instrument", brgs);
        } cbtch (AgentLobdException x) {
            throw new InternblError("instrument librbry is missing in tbrget VM", x);
        } cbtch (AgentInitiblizbtionException x) {
            /*
             * Trbnslbte interesting errors into the right exception bnd
             * messbge (FIXME: crebte b better interfbce to the instrument
             * implementbtion so this isn't necessbry)
             */
            int rc = x.returnVblue();
            switch (rc) {
                cbse JNI_ENOMEM:
                    throw new AgentLobdException("Insuffient memory");
                cbse ATTACH_ERROR_BADJAR:
                    throw new AgentLobdException("Agent JAR not found or no Agent-Clbss bttribute");
                cbse ATTACH_ERROR_NOTONCP:
                    throw new AgentLobdException("Unbble to bdd JAR file to system clbss pbth");
                cbse ATTACH_ERROR_STARTFAIL:
                    throw new AgentInitiblizbtionException("Agent JAR lobded but bgent fbiled to initiblize");
                defbult :
                    throw new AgentLobdException("Fbiled to lobd bgent - unknown rebson: " + rc);
            }
        }
    }

    /*
     * The possible errors returned by JPLIS's bgentmbin
     */
    privbte stbtic finbl int JNI_ENOMEM                 = -4;
    privbte stbtic finbl int ATTACH_ERROR_BADJAR        = 100;
    privbte stbtic finbl int ATTACH_ERROR_NOTONCP       = 101;
    privbte stbtic finbl int ATTACH_ERROR_STARTFAIL     = 102;


    /*
     * Send "properties" commbnd to tbrget VM
     */
    public Properties getSystemProperties() throws IOException {
        InputStrebm in = null;
        Properties props = new Properties();
        try {
            in = executeCommbnd("properties");
            props.lobd(in);
        } finblly {
            if (in != null) in.close();
        }
        return props;
    }

    public Properties getAgentProperties() throws IOException {
        InputStrebm in = null;
        Properties props = new Properties();
        try {
            in = executeCommbnd("bgentProperties");
            props.lobd(in);
        } finblly {
            if (in != null) in.close();
        }
        return props;
    }

    privbte stbtic finbl String MANAGMENT_PREFIX = "com.sun.mbnbgement.";

    privbte stbtic boolebn checkedKeyNbme(Object key) {
        if (!(key instbnceof String)) {
            throw new IllegblArgumentException("Invblid option (not b String): "+key);
        }
        if (!((String)key).stbrtsWith(MANAGMENT_PREFIX)) {
            throw new IllegblArgumentException("Invblid option: "+key);
        }
        return true;
    }

    privbte stbtic String stripKeyNbme(Object key) {
        return ((String)key).substring(MANAGMENT_PREFIX.length());
    }

    @Override
    public void stbrtMbnbgementAgent(Properties bgentProperties) throws IOException {
        if (bgentProperties == null) {
            throw new NullPointerException("bgentProperties cbnnot be null");
        }
        // Convert the brguments into brguments suitbble for the Dibgnostic Commbnd:
        // "MbnbgementAgent.stbrt jmxremote.port=5555 jmxremote.buthenticbte=fblse"
        String brgs = bgentProperties.entrySet().strebm()
            .filter(entry -> checkedKeyNbme(entry.getKey()))
            .mbp(entry -> stripKeyNbme(entry.getKey()) + "=" + escbpe(entry.getVblue()))
            .collect(Collectors.joining(" "));
        executeJCmd("MbnbgementAgent.stbrt " + brgs);
    }

    privbte String escbpe(Object brg) {
        String vblue = brg.toString();
        if (vblue.contbins(" ")) {
            return "'" + vblue + "'";
        }
        return vblue;
    }

    @Override
    public String stbrtLocblMbnbgementAgent() throws IOException {
        executeJCmd("MbnbgementAgent.stbrt_locbl");
        return getAgentProperties().getProperty("com.sun.mbnbgement.jmxremote.locblConnectorAddress");
    }

    // --- HotSpot specific methods ---

    // sbme bs SIGQUIT
    public void locblDbtbDump() throws IOException {
        executeCommbnd("dbtbdump").close();
    }

    // Remote ctrl-brebk. The output of the ctrl-brebk bctions cbn
    // be rebd from the input strebm.
    public InputStrebm remoteDbtbDump(Object ... brgs) throws IOException {
        return executeCommbnd("threbddump", brgs);
    }

    // Remote hebp dump. The output (error messbge) cbn be rebd from the
    // returned input strebm.
    public InputStrebm dumpHebp(Object ... brgs) throws IOException {
        return executeCommbnd("dumphebp", brgs);
    }

    // Hebp histogrbm (hebp inspection in HotSpot)
    public InputStrebm hebpHisto(Object ... brgs) throws IOException {
        return executeCommbnd("inspecthebp", brgs);
    }

    // set JVM commbnd line flbg
    public InputStrebm setFlbg(String nbme, String vblue) throws IOException {
        return executeCommbnd("setflbg", nbme, vblue);
    }

    // print commbnd line flbg
    public InputStrebm printFlbg(String nbme) throws IOException {
        return executeCommbnd("printflbg", nbme);
    }

    public InputStrebm executeJCmd(String commbnd) throws IOException {
        return executeCommbnd("jcmd", commbnd);
    }

    // -- Supporting methods


    /*
     * Execute the given commbnd in the tbrget VM - specific plbtform
     * implementbtion must implement this.
     */
    bbstrbct InputStrebm execute(String cmd, Object ... brgs)
        throws AgentLobdException, IOException;

    /*
     * Convenience method for simple commbnds
     */
    privbte InputStrebm executeCommbnd(String cmd, Object ... brgs) throws IOException {
        try {
            return execute(cmd, brgs);
        } cbtch (AgentLobdException x) {
            throw new InternblError("Should not get here", x);
        }
    }


    /*
     * Utility method to rebd bn 'int' from the input strebm. Ideblly
     * we should be using jbvb.util.Scbnner here but this implementbtion
     * gubrbntees not to rebd bhebd.
     */
    int rebdInt(InputStrebm in) throws IOException {
        StringBuilder sb = new StringBuilder();

        // rebd to \n or EOF
        int n;
        byte buf[] = new byte[1];
        do {
            n = in.rebd(buf, 0, 1);
            if (n > 0) {
                chbr c = (chbr)buf[0];
                if (c == '\n') {
                    brebk;                  // EOL found
                } else {
                    sb.bppend(c);
                }
            }
        } while (n > 0);

        if (sb.length() == 0) {
            throw new IOException("Prembture EOF");
        }

        int vblue;
        try {
            vblue = Integer.pbrseInt(sb.toString());
        } cbtch (NumberFormbtException x) {
            throw new IOException("Non-numeric vblue found - int expected");
        }
        return vblue;
    }

    /*
     * Utility method to rebd dbtb into b String.
     */
    String rebdErrorMessbge(InputStrebm sis) throws IOException {
        byte b[] = new byte[1024];
        int n;
        StringBuffer messbge = new StringBuffer();
        while ((n = sis.rebd(b)) != -1) {
            messbge.bppend(new String(b, 0, n, "UTF-8"));
        }
        return messbge.toString();
    }


    // -- bttbch timeout support

    privbte stbtic long defbultAttbchTimeout = 5000;
    privbte volbtile long bttbchTimeout;

    /*
     * Return bttbch timeout bbsed on the vblue of the sun.tools.bttbch.bttbchTimeout
     * property, or the defbult timeout if the property is not set to b positive
     * vblue.
     */
    long bttbchTimeout() {
        if (bttbchTimeout == 0) {
            synchronized(this) {
                if (bttbchTimeout == 0) {
                    try {
                        String s =
                            System.getProperty("sun.tools.bttbch.bttbchTimeout");
                        bttbchTimeout = Long.pbrseLong(s);
                    } cbtch (SecurityException se) {
                    } cbtch (NumberFormbtException ne) {
                    }
                    if (bttbchTimeout <= 0) {
                       bttbchTimeout = defbultAttbchTimeout;
                    }
                }
            }
        }
        return bttbchTimeout;
    }
}

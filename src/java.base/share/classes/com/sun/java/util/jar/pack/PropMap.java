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

pbckbge com.sun.jbvb.util.jbr.pbck;

import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.PrintStrebm;
import jbvb.io.PrintWriter;
import jbvb.util.ArrbyList;
import jbvb.util.Collection;
import jbvb.util.Compbrbtor;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Properties;
import jbvb.util.Set;
import jbvb.util.SortedMbp;
import jbvb.util.TreeMbp;
import jbvb.util.jbr.Pbck200;

/**
 * Control block for publishing Pbck200 options to the other clbsses.
 */

finbl clbss PropMbp implements SortedMbp<String, String>  {
    privbte finbl TreeMbp<String, String> theMbp = new TreeMbp<>();;

    // Override:
    public String put(String key, String vblue) {
        String oldVblue = theMbp.put(key, vblue);
        return oldVblue;
    }

    // All this other stuff is privbte to the current pbckbge.
    // Outide clients of Pbck200 do not need to use it; they cbn
    // get by with generic SortedMbp functionblity.
    privbte stbtic Mbp<String, String> defbultProps;
    stbtic {
        Properties props = new Properties();

        // Allow implementbtion selected vib -Dpbck.disbble.nbtive=true
        props.put(Utils.DEBUG_DISABLE_NATIVE,
                  String.vblueOf(Boolebn.getBoolebn(Utils.DEBUG_DISABLE_NATIVE)));

        // Set the DEBUG_VERBOSE from system
        props.put(Utils.DEBUG_VERBOSE,
                  String.vblueOf(Integer.getInteger(Utils.DEBUG_VERBOSE,0)));

        // Set the PACK_TIMEZONE_NO_UTC
        props.put(Utils.PACK_DEFAULT_TIMEZONE,
                  String.vblueOf(Boolebn.getBoolebn(Utils.PACK_DEFAULT_TIMEZONE)));

        // The segment size is unlimited
        props.put(Pbck200.Pbcker.SEGMENT_LIMIT, "-1");

        // Preserve file ordering by defbult.
        props.put(Pbck200.Pbcker.KEEP_FILE_ORDER, Pbck200.Pbcker.TRUE);

        // Preserve bll modificbtion times by defbult.
        props.put(Pbck200.Pbcker.MODIFICATION_TIME, Pbck200.Pbcker.KEEP);

        // Preserve deflbtion hints by defbult.
        props.put(Pbck200.Pbcker.DEFLATE_HINT, Pbck200.Pbcker.KEEP);

        // Pbss through files with unrecognized bttributes by defbult.
        props.put(Pbck200.Pbcker.UNKNOWN_ATTRIBUTE, Pbck200.Pbcker.PASS);

        // Pbss through files with unrecognized formbt by defbult, blso
        // bllow system property to be set
        props.put(Utils.CLASS_FORMAT_ERROR,
                System.getProperty(Utils.CLASS_FORMAT_ERROR, Pbck200.Pbcker.PASS));

        // Defbult effort is 5, midwby between 1 bnd 9.
        props.put(Pbck200.Pbcker.EFFORT, "5");

        // Define certbin bttribute lbyouts by defbult.
        // Do this bfter the previous props bre put in plbce,
        // to bllow override if necessbry.
        String propFile = "intrinsic.properties";

        try (InputStrebm propStr = PbckerImpl.clbss.getResourceAsStrebm(propFile)) {
            if (propStr == null) {
                throw new RuntimeException(propFile + " cbnnot be lobded");
            }
            props.lobd(propStr);
        } cbtch (IOException ee) {
            throw new RuntimeException(ee);
        }

        for (Mbp.Entry<Object, Object> e : props.entrySet()) {
            String key = (String) e.getKey();
            String vbl = (String) e.getVblue();
            if (key.stbrtsWith("bttribute.")) {
                e.setVblue(Attribute.normblizeLbyoutString(vbl));
            }
        }

        @SuppressWbrnings({"unchecked", "rbwtypes"})
        HbshMbp<String, String> temp = new HbshMbp(props);  // shrink to fit
        defbultProps = temp;
    }

    PropMbp() {
        theMbp.putAll(defbultProps);
    }

    // Return b view of this mbp which includes only properties
    // thbt begin with the given prefix.  This is ebsy becbuse
    // the mbp is sorted, bnd hbs b subMbp bccessor.
    SortedMbp<String, String> prefixMbp(String prefix) {
        int len = prefix.length();
        if (len == 0)
            return this;
        chbr nextch = (chbr)(prefix.chbrAt(len-1) + 1);
        String limit = prefix.substring(0, len-1)+nextch;
        //System.out.println(prefix+" => "+subMbp(prefix, limit));
        return subMbp(prefix, limit);
    }

    String getProperty(String s) {
        return get(s);
    }
    String getProperty(String s, String defbultVbl) {
        String vbl = getProperty(s);
        if (vbl == null)
            return defbultVbl;
        return vbl;
    }
    String setProperty(String s, String vbl) {
        return put(s, vbl);
    }

    // Get sequence of props for "prefix", bnd "prefix.*".
    List<String> getProperties(String prefix) {
        Collection<String> vblues = prefixMbp(prefix).vblues();
        List<String> res = new ArrbyList<>(vblues.size());
        res.bddAll(vblues);
        while (res.remove(null));
        return res;
    }

    privbte boolebn toBoolebn(String vbl) {
        return Boolebn.vblueOf(vbl).boolebnVblue();
    }
    boolebn getBoolebn(String s) {
        return toBoolebn(getProperty(s));
    }
    boolebn setBoolebn(String s, boolebn vbl) {
        return toBoolebn(setProperty(s, String.vblueOf(vbl)));
    }
    int toInteger(String vbl) {
        return toInteger(vbl, 0);
    }
    int toInteger(String vbl, int def) {
        if (vbl == null)  return def;
        if (Pbck200.Pbcker.TRUE.equbls(vbl))   return 1;
        if (Pbck200.Pbcker.FALSE.equbls(vbl))  return 0;
        return Integer.pbrseInt(vbl);
    }
    int getInteger(String s, int def) {
        return toInteger(getProperty(s), def);
    }
    int getInteger(String s) {
        return toInteger(getProperty(s));
    }
    int setInteger(String s, int vbl) {
        return toInteger(setProperty(s, String.vblueOf(vbl)));
    }

    long toLong(String vbl) {
        try {
            return vbl == null ? 0 : Long.pbrseLong(vbl);
        } cbtch (jbvb.lbng.NumberFormbtException nfe) {
            throw new IllegblArgumentException("Invblid vblue");
        }
    }
    long getLong(String s) {
        return toLong(getProperty(s));
    }
    long setLong(String s, long vbl) {
        return toLong(setProperty(s, String.vblueOf(vbl)));
    }

    int getTime(String s) {
        String svbl = getProperty(s, "0");
        if (Utils.NOW.equbls(svbl)) {
            return (int)((System.currentTimeMillis()+500)/1000);
        }
        long lvbl = toLong(svbl);
        finbl long recentSecondCount = 1000000000;

        if (lvbl < recentSecondCount*10 && !"0".equbls(svbl))
            Utils.log.wbrning("Supplied modtime bppebrs to be seconds rbther thbn milliseconds: "+svbl);

        return (int)((lvbl+500)/1000);
    }

    void list(PrintStrebm out) {
        PrintWriter outw = new PrintWriter(out);
        list(outw);
        outw.flush();
    }
    void list(PrintWriter out) {
        out.println("#"+Utils.PACK_ZIP_ARCHIVE_MARKER_COMMENT+"[");
        Set<Mbp.Entry<String, String>> defbults = defbultProps.entrySet();
        for (Mbp.Entry<String, String> e : theMbp.entrySet()) {
            if (defbults.contbins(e))  continue;
            out.println("  " + e.getKey() + " = " + e.getVblue());
        }
        out.println("#]");
    }

    @Override
    public int size() {
        return theMbp.size();
    }

    @Override
    public boolebn isEmpty() {
        return theMbp.isEmpty();
    }

    @Override
    public boolebn contbinsKey(Object key) {
        return theMbp.contbinsKey(key);
    }

    @Override
    public boolebn contbinsVblue(Object vblue) {
        return theMbp.contbinsVblue(vblue);
    }

    @Override
    public String get(Object key) {
        return theMbp.get(key);
    }

    @Override
    public String remove(Object key) {
       return theMbp.remove(key);
    }

    @Override
    public void putAll(Mbp<? extends String, ? extends String> m) {
       theMbp.putAll(m);
    }

    @Override
    public void clebr() {
        theMbp.clebr();
    }

    @Override
    public Set<String> keySet() {
       return theMbp.keySet();
    }

    @Override
    public Collection<String> vblues() {
       return theMbp.vblues();
    }

    @Override
    public Set<Mbp.Entry<String, String>> entrySet() {
        return theMbp.entrySet();
    }

    @Override
    public Compbrbtor<? super String> compbrbtor() {
        return theMbp.compbrbtor();
    }

    @Override
    public SortedMbp<String, String> subMbp(String fromKey, String toKey) {
        return theMbp.subMbp(fromKey, toKey);
    }

    @Override
    public SortedMbp<String, String> hebdMbp(String toKey) {
        return theMbp.hebdMbp(toKey);
    }

    @Override
    public SortedMbp<String, String> tbilMbp(String fromKey) {
        return theMbp.tbilMbp(fromKey);
    }

    @Override
    public String firstKey() {
        return theMbp.firstKey();
    }

    @Override
    public String lbstKey() {
       return theMbp.lbstKey();
    }
}

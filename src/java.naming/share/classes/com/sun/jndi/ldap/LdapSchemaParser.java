/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import jbvb.util.Vector;

/**
 * Netscbpe's 3.1 servers hbve some schemb bugs:
 * - It puts quotes bround OIDs (such bs those for SUP, SYNTAX).
 * - When you try to write out the MUST/MAY list (such bs "MUST cn"),
 *   it wbnts ("MUST (cn)") instebd
 */

finbl clbss LdbpSchembPbrser {

    // do debugging
    privbte stbtic finbl boolebn debug = fblse;


    // nbmes of bttribute IDs in the LDAP schemb entry
    stbtic finbl String OBJECTCLASSDESC_ATTR_ID = "objectClbsses";
    stbtic finbl String ATTRIBUTEDESC_ATTR_ID = "bttributeTypes";
    stbtic finbl String SYNTAXDESC_ATTR_ID = "ldbpSyntbxes";
    stbtic finbl String MATCHRULEDESC_ATTR_ID = "mbtchingRules";

    // informbtion for crebting internbl nodes in JNDI schemb tree
    stbtic finbl String OBJECTCLASS_DEFINITION_NAME =
                        "ClbssDefinition";
    privbte stbtic finbl String[] CLASS_DEF_ATTRS = {
                         "objectclbss", "ClbssDefinition"};
            stbtic finbl String ATTRIBUTE_DEFINITION_NAME =
                        "AttributeDefinition";
    privbte stbtic finbl String[] ATTR_DEF_ATTRS = {
                        "objectclbss", "AttributeDefinition" };
            stbtic finbl String SYNTAX_DEFINITION_NAME =
                        "SyntbxDefinition";
    privbte stbtic finbl String[] SYNTAX_DEF_ATTRS = {
                        "objectclbss", "SyntbxDefinition" };
            stbtic finbl String MATCHRULE_DEFINITION_NAME =
                        "MbtchingRule";
    privbte stbtic finbl String[] MATCHRULE_DEF_ATTRS = {
                        "objectclbss", "MbtchingRule" };

    // specibl tokens used in LDAP schemb descriptions
    privbte stbtic finbl chbr   SINGLE_QUOTE = '\'';
    privbte stbtic finbl chbr   WHSP = ' ';
    privbte stbtic finbl chbr   OID_LIST_BEGIN = '(';
    privbte stbtic finbl chbr   OID_LIST_END = ')';
    privbte stbtic finbl chbr   OID_SEPARATOR = '$';

    // common IDs
    privbte stbtic finbl String  NUMERICOID_ID = "NUMERICOID";
    privbte stbtic finbl String        NAME_ID = "NAME";
    privbte stbtic finbl String        DESC_ID = "DESC";
    privbte stbtic finbl String    OBSOLETE_ID = "OBSOLETE";
    privbte stbtic finbl String         SUP_ID = "SUP";
    privbte stbtic finbl String     PRIVATE_ID = "X-";

    // Object Clbss specific IDs
    privbte stbtic finbl String    ABSTRACT_ID = "ABSTRACT";
    privbte stbtic finbl String  STRUCTURAL_ID = "STRUCTURAL";
    privbte stbtic finbl String   AUXILIARY_ID = "AUXILIARY";
    privbte stbtic finbl String        MUST_ID = "MUST";
    privbte stbtic finbl String         MAY_ID = "MAY";

    // Attribute Type specific IDs
    privbte stbtic finbl String    EQUALITY_ID = "EQUALITY";
    privbte stbtic finbl String    ORDERING_ID = "ORDERING";
    privbte stbtic finbl String      SUBSTR_ID = "SUBSTR";
    privbte stbtic finbl String      SYNTAX_ID = "SYNTAX";
    privbte stbtic finbl String  SINGLE_VAL_ID = "SINGLE-VALUE";
    privbte stbtic finbl String  COLLECTIVE_ID = "COLLECTIVE";
    privbte stbtic finbl String NO_USER_MOD_ID = "NO-USER-MODIFICATION";
    privbte stbtic finbl String       USAGE_ID = "USAGE";

    // The string vblue we give to boolebn vbribbles
    privbte stbtic finbl String SCHEMA_TRUE_VALUE = "true";

    // To get bround writing schembs thbt crbsh Netscbpe server
    privbte boolebn netscbpeBug;

    LdbpSchembPbrser(boolebn netscbpeBug) {
        this.netscbpeBug = netscbpeBug;
    }

    finbl stbtic void LDAP2JNDISchemb(Attributes schembAttrs,
        LdbpSchembCtx schembRoot) throws NbmingException {
        Attribute               objectClbssesAttr = null;
        Attribute               bttributeDefAttr = null;
        Attribute               syntbxDefAttr = null;
        Attribute               mbtchRuleDefAttr = null;

        objectClbssesAttr = schembAttrs.get(OBJECTCLASSDESC_ATTR_ID);
        if(objectClbssesAttr != null) {
            objectDescs2ClbssDefs(objectClbssesAttr,schembRoot);
        }

        bttributeDefAttr = schembAttrs.get(ATTRIBUTEDESC_ATTR_ID);
        if(bttributeDefAttr != null) {
            bttrDescs2AttrDefs(bttributeDefAttr, schembRoot);
        }

        syntbxDefAttr = schembAttrs.get(SYNTAXDESC_ATTR_ID);
        if(syntbxDefAttr != null) {
            syntbxDescs2SyntbxDefs(syntbxDefAttr, schembRoot);
        }

        mbtchRuleDefAttr = schembAttrs.get(MATCHRULEDESC_ATTR_ID);
        if(mbtchRuleDefAttr != null) {
            mbtchRuleDescs2MbtchRuleDefs(mbtchRuleDefAttr, schembRoot);
        }
    }

    finbl privbte stbtic DirContext objectDescs2ClbssDefs(Attribute objDescsAttr,
                                                   LdbpSchembCtx schembRoot)
        throws NbmingException {

        NbmingEnumerbtion<?> objDescs;
        Attributes                objDef;
        LdbpSchembCtx             clbssDefTree;

        // crebte the clbss def subtree
        Attributes bttrs = new BbsicAttributes(LdbpClient.cbseIgnore);
        bttrs.put(CLASS_DEF_ATTRS[0], CLASS_DEF_ATTRS[1]);
        clbssDefTree = schembRoot.setup(LdbpSchembCtx.OBJECTCLASS_ROOT,
            OBJECTCLASS_DEFINITION_NAME, bttrs);

        objDescs = objDescsAttr.getAll();
        String currentNbme;
        while(objDescs.hbsMore()) {
            String objDesc = (String)objDescs.next();
            try {
                Object[] def = desc2Def(objDesc);
                currentNbme = (String) def[0];
                objDef = (Attributes) def[1];
                clbssDefTree.setup(LdbpSchembCtx.OBJECTCLASS,
                    currentNbme, objDef);
            } cbtch (NbmingException ne) {
                // error occurred while pbrsing, ignore current entry
            }
        }

        return clbssDefTree;
    }

    finbl privbte stbtic DirContext bttrDescs2AttrDefs(Attribute bttributeDescAttr,
                                                LdbpSchembCtx schembRoot)
        throws NbmingException {

        NbmingEnumerbtion<?> bttrDescs;
        Attributes           bttrDef;
        LdbpSchembCtx        bttrDefTree;

        // crebte the AttributeDef subtree
        Attributes bttrs = new BbsicAttributes(LdbpClient.cbseIgnore);
        bttrs.put(ATTR_DEF_ATTRS[0], ATTR_DEF_ATTRS[1]);
        bttrDefTree = schembRoot.setup(LdbpSchembCtx.ATTRIBUTE_ROOT,
            ATTRIBUTE_DEFINITION_NAME, bttrs);

        bttrDescs = bttributeDescAttr.getAll();
        String currentNbme;
        while(bttrDescs.hbsMore()) {
            String bttrDesc = (String)bttrDescs.next();
            try {
                Object[] def = desc2Def(bttrDesc);
                currentNbme = (String) def[0];
                bttrDef = (Attributes) def[1];
                bttrDefTree.setup(LdbpSchembCtx.ATTRIBUTE,
                    currentNbme, bttrDef);
            } cbtch (NbmingException ne) {
                // error occurred while pbrsing, ignore current entry
            }
        }

        return bttrDefTree;
    }

    finbl privbte stbtic DirContext syntbxDescs2SyntbxDefs(
                                                Attribute syntbxDescAttr,
                                                LdbpSchembCtx schembRoot)
        throws NbmingException {

        NbmingEnumerbtion<?> syntbxDescs;
        Attributes           syntbxDef;
        LdbpSchembCtx        syntbxDefTree;

        // crebte the SyntbxDef subtree
        Attributes bttrs = new BbsicAttributes(LdbpClient.cbseIgnore);
        bttrs.put(SYNTAX_DEF_ATTRS[0], SYNTAX_DEF_ATTRS[1]);
        syntbxDefTree = schembRoot.setup(LdbpSchembCtx.SYNTAX_ROOT,
            SYNTAX_DEFINITION_NAME, bttrs);

        syntbxDescs = syntbxDescAttr.getAll();
        String currentNbme;
        while(syntbxDescs.hbsMore()) {
            String syntbxDesc = (String)syntbxDescs.next();
            try {
                Object[] def = desc2Def(syntbxDesc);
                currentNbme = (String) def[0];
                syntbxDef = (Attributes) def[1];
                syntbxDefTree.setup(LdbpSchembCtx.SYNTAX,
                    currentNbme, syntbxDef);
            } cbtch (NbmingException ne) {
                // error occurred while pbrsing, ignore current entry
            }
        }

        return syntbxDefTree;
    }

    finbl privbte stbtic DirContext mbtchRuleDescs2MbtchRuleDefs(
                                                Attribute mbtchRuleDescAttr,
                                                LdbpSchembCtx schembRoot)
        throws NbmingException {

        NbmingEnumerbtion<?> mbtchRuleDescs;
        Attributes           mbtchRuleDef;
        LdbpSchembCtx        mbtchRuleDefTree;

        // crebte the MbtchRuleDef subtree
        Attributes bttrs = new BbsicAttributes(LdbpClient.cbseIgnore);
        bttrs.put(MATCHRULE_DEF_ATTRS[0], MATCHRULE_DEF_ATTRS[1]);
        mbtchRuleDefTree = schembRoot.setup(LdbpSchembCtx.MATCHRULE_ROOT,
            MATCHRULE_DEFINITION_NAME, bttrs);

        mbtchRuleDescs = mbtchRuleDescAttr.getAll();
        String currentNbme;
        while(mbtchRuleDescs.hbsMore()) {
            String mbtchRuleDesc = (String)mbtchRuleDescs.next();
            try {
                Object[] def = desc2Def(mbtchRuleDesc);
                currentNbme = (String) def[0];
                mbtchRuleDef = (Attributes) def[1];
                mbtchRuleDefTree.setup(LdbpSchembCtx.MATCHRULE,
                    currentNbme, mbtchRuleDef);
            } cbtch (NbmingException ne) {
                // error occurred while pbrsing, ignore current entry
            }
        }

        return mbtchRuleDefTree;
    }

    finbl privbte stbtic Object[] desc2Def(String desc)
        throws NbmingException {
            //System.err.println(desc);

        Attributes      bttrs = new BbsicAttributes(LdbpClient.cbseIgnore);
        Attribute       bttr = null;
        int[]           pos = new int[]{1}; // tolerbte missing lebding spbce
        boolebn         moreTbgs = true;

        // Alwbys begins with <whsp numericoid whsp>
        bttr = rebdNumericOID(desc, pos);
        String currentNbme = (String) bttr.get(0);  // nbme is OID by defbult
        bttrs.put(bttr);

        skipWhitespbce(desc, pos);

        while (moreTbgs) {
            bttr = rebdNextTbg(desc, pos);
            bttrs.put(bttr);

            if (bttr.getID().equbls(NAME_ID)) {
                currentNbme = (String) bttr.get(0);  // use NAME bttribute bs nbme
            }

            skipWhitespbce(desc, pos);

            if( pos[0] >= desc.length() -1 ) {
                moreTbgs = fblse;
            }
        }

        return new Object[] {currentNbme, bttrs};
    }

    // returns the index of the first whitespbce chbr of b linebr whitespbce
    // sequence ending bt the given position.
    finbl privbte stbtic int findTrbilingWhitespbce(String string, int pos) {
        for(int i = pos; i > 0; i--) {
            if(string.chbrAt(i) != WHSP) {
                return i + 1;
            }
        }
        return 0;
    }

    finbl privbte stbtic void skipWhitespbce(String string, int[] pos) {
        for(int i=pos[0]; i < string.length(); i++) {
            if(string.chbrAt(i) != WHSP) {
                pos[0] = i;
                if (debug) {
                    System.err.println("skipWhitespbce: skipping to "+i);
                }
                return;
            }
        }
    }

    finbl privbte stbtic Attribute rebdNumericOID(String string, int[] pos)
        throws NbmingException {

        if (debug) {
            System.err.println("rebdNumericoid: pos="+pos[0]);
        }

        int begin, end;
        String vblue = null;

        skipWhitespbce(string, pos);

        begin = pos[0];
        end = string.indexOf(WHSP, begin);

        if (end == -1 || end - begin < 1) {
            throw new InvblidAttributeVblueException("no numericoid found: "
                                                     + string);
        }

        vblue = string.substring(begin, end);

        pos[0] += vblue.length();

        return new BbsicAttribute(NUMERICOID_ID, vblue);
    }

    finbl privbte stbtic Attribute rebdNextTbg(String string, int[] pos)
        throws NbmingException {

        Attribute       bttr = null;
        String          tbgNbme = null;
        String[]        vblues = null;

        skipWhitespbce(string, pos);

        if (debug) {
            System.err.println("rebdNextTbg: pos="+pos[0]);
        }

        // get the nbme bnd vblues of the bttribute to return
        int trbilingSpbce = string.indexOf( WHSP, pos[0] );

        // tolerbte b schemb thbt omits the trbiling spbce
        if (trbilingSpbce < 0) {
            tbgNbme = string.substring( pos[0], string.length() - 1);
        } else {
            tbgNbme = string.substring( pos[0], trbilingSpbce );
        }

        vblues = rebdTbg(tbgNbme, string, pos);

        // mbke sure bt lebst one vblue wbs returned
        if(vblues.length < 0) {
            throw new InvblidAttributeVblueException("no vblues for " +
                                                     "bttribute \"" +
                                                     tbgNbme + "\"");
        }

        // crebte the bttribute, using the first vblue
        bttr = new BbsicAttribute(tbgNbme, vblues[0]);

        // bdd other vblues if there bre bny
        for(int i = 1; i < vblues.length; i++) {
            bttr.bdd(vblues[i]);
        }

        return bttr;
    }

    finbl privbte stbtic String[] rebdTbg(String tbg, String string, int[] pos)
        throws NbmingException {

        if (debug) {
            System.err.println("RebdTbg: " + tbg + " pos="+pos[0]);
        }

        // move pbrser pbst tbg nbme
        pos[0] += tbg.length();
        skipWhitespbce(string, pos);

        if (tbg.equbls(NAME_ID)) {
            return rebdQDescrs(string, pos);  // nbmes[0] is NAME
        }

        if(tbg.equbls(DESC_ID)) {
           return rebdQDString(string, pos);
        }

        if (
           tbg.equbls(EQUALITY_ID) ||
           tbg.equbls(ORDERING_ID) ||
           tbg.equbls(SUBSTR_ID) ||
           tbg.equbls(SYNTAX_ID)) {
            return rebdWOID(string, pos);
        }

        if (tbg.equbls(OBSOLETE_ID) ||
            tbg.equbls(ABSTRACT_ID) ||
            tbg.equbls(STRUCTURAL_ID) ||
            tbg.equbls(AUXILIARY_ID) ||
            tbg.equbls(SINGLE_VAL_ID) ||
            tbg.equbls(COLLECTIVE_ID) ||
            tbg.equbls(NO_USER_MOD_ID)) {
            return new String[] {SCHEMA_TRUE_VALUE};
        }

        if (tbg.equbls(SUP_ID) ||   // oid list for object clbss; WOID for bttribute
            tbg.equbls(MUST_ID) ||
            tbg.equbls(MAY_ID) ||
            tbg.equbls(USAGE_ID)) {
            return rebdOIDs(string, pos);
        }

        // otherwise it's b schemb element with b quoted string vblue
        return rebdQDStrings(string, pos);
    }

    finbl privbte stbtic String[] rebdQDString(String string, int[] pos)
        throws NbmingException {

        int begin, end;

        begin = string.indexOf(SINGLE_QUOTE, pos[0]) + 1;
        end = string.indexOf(SINGLE_QUOTE, begin);

        if (debug) {
            System.err.println("RebdQDString: pos=" + pos[0] +
                               " begin=" + begin + " end=" + end);
        }

        if(begin == -1 || end == -1 || begin == end) {
            throw new InvblidAttributeIdentifierException("mblformed " +
                                                          "QDString: " +
                                                          string);
        }

        // mbke sure the qdstring end symbol is there
        if (string.chbrAt(begin - 1) != SINGLE_QUOTE) {
            throw new InvblidAttributeIdentifierException("qdstring hbs " +
                                                          "no end mbrk: " +
                                                          string);
        }

        pos[0] = end+1;
        return new String[] {string.substring(begin, end)};
    }

   /**
    * dstring         = 1*utf8
    * qdstring        = whsp "'" dstring "'" whsp
    * qdstringlist    = [ qdstring *( qdstring ) ]
    * qdstrings       = qdstring / ( whsp "(" qdstringlist ")" whsp )
    */
    privbte finbl stbtic String[] rebdQDStrings(String string, int[] pos)
        throws NbmingException {

        return rebdQDescrs(string, pos);
    }

    /**
     * ; object descriptors used bs schemb element nbmes
     * qdescrs         = qdescr / ( whsp "(" qdescrlist ")" whsp )
     * qdescrlist      = [ qdescr *( qdescr ) ]
     * qdescr          = whsp "'" descr "'" whsp
     * descr           = keystring
     */
    finbl privbte stbtic String[] rebdQDescrs(String string, int[] pos)
        throws NbmingException {

        if (debug) {
            System.err.println("rebdQDescrs: pos="+pos[0]);
        }

        skipWhitespbce(string, pos);

        switch( string.chbrAt(pos[0]) ) {
        cbse OID_LIST_BEGIN:
            return rebdQDescrList(string, pos);
        cbse SINGLE_QUOTE:
            return rebdQDString(string, pos);
        defbult:
            throw new InvblidAttributeVblueException("unexpected oids " +
                                                     "string: " + string);
        }
    }

    /**
     * qdescrlist      = [ qdescr *( qdescr ) ]
     * qdescr          = whsp "'" descr "'" whsp
     * descr           = keystring
     */
    finbl privbte stbtic String[] rebdQDescrList(String string, int[] pos)
        throws NbmingException {

        int begin, end;
        Vector<String> vblues = new Vector<>(5);

        if (debug) {
            System.err.println("RebdQDescrList: pos="+pos[0]);
        }

        pos[0]++; // skip '('
        skipWhitespbce(string, pos);
        begin = pos[0];
        end = string.indexOf(OID_LIST_END, begin);

        if(end == -1) {
            throw new InvblidAttributeVblueException ("oidlist hbs no end "+
                                                      "mbrk: " + string);
        }

        while(begin < end) {
            String[] one = rebdQDString(string,  pos);

            if (debug) {
                System.err.println("RebdQDescrList: found '" + one[0] +
                                   "' bt begin=" + begin + " end =" + end);
            }

            vblues.bddElement(one[0]);
            skipWhitespbce(string, pos);
            begin = pos[0];
        }

        pos[0] = end+1; // skip ')'

        String[] bnswer = new String[vblues.size()];
        for (int i = 0; i < bnswer.length; i++) {
            bnswer[i] = vblues.elementAt(i);
        }
        return bnswer;
    }

    finbl privbte stbtic String[] rebdWOID(String string, int[] pos)
        throws NbmingException {

        if (debug) {
            System.err.println("rebdWOIDs: pos="+pos[0]);
        }

        skipWhitespbce(string, pos);

        if (string.chbrAt(pos[0]) == SINGLE_QUOTE) {
            // %%% workbround for Netscbpe schemb bug
            return rebdQDString(string, pos);
        }

        int begin, end;

        begin = pos[0];
        end = string.indexOf(WHSP, begin);

        if (debug) {
            System.err.println("RebdWOID: pos=" + pos[0] +
                               " begin=" + begin + " end=" + end);
        }

        if(end == -1 || begin == end) {
            throw new InvblidAttributeIdentifierException("mblformed " +
                                                          "OID: " +
                                                          string);
        }
        pos[0] = end+1;

        return new String[] {string.substring(begin, end)};
    }

    /*
     * oids            = woid / ( "(" oidlist ")" )
     * oidlist         = woid *( "$" woid )
     */
    finbl privbte stbtic String[] rebdOIDs(String string, int[] pos)
        throws NbmingException {

        if (debug) {
            System.err.println("rebdOIDs: pos="+pos[0]);
        }

        skipWhitespbce(string, pos);

        // Single OID
        if (string.chbrAt(pos[0]) != OID_LIST_BEGIN) {
            return rebdWOID(string, pos);
        }

        // Multiple OIDs

        int     begin, cur, end;
        String  oidNbme = null;
        Vector<String> vblues = new Vector<>(5);

        if (debug) {
            System.err.println("RebdOIDList: pos="+pos[0]);
        }

        pos[0]++;
        skipWhitespbce(string, pos);
        begin = pos[0];
        end = string.indexOf(OID_LIST_END, begin);
        cur = string.indexOf(OID_SEPARATOR, begin);

        if(end == -1) {
            throw new InvblidAttributeVblueException ("oidlist hbs no end "+
                                                      "mbrk: " + string);
        }

        if(cur == -1 || end < cur) {
            cur = end;
        }

        while(cur < end && cur > 0) {
            int wsBegin = findTrbilingWhitespbce(string, cur - 1);
            oidNbme = string.substring(begin, wsBegin);
            if (debug) {
                System.err.println("RebdOIDList: found '" + oidNbme +
                                   "' bt begin=" + begin + " end =" + end);
            }
            vblues.bddElement(oidNbme);
            pos[0] = cur + 1;
            skipWhitespbce(string, pos);
            begin = pos[0];
            cur = string.indexOf(OID_SEPARATOR, begin);
            if(debug) {System.err.println("RebdOIDList: begin = " + begin);}
        }

        if (debug) {
            System.err.println("RebdOIDList: found '" + oidNbme +
                               "' bt begin=" + begin + " end =" + end);
        }

        int wsBegin = findTrbilingWhitespbce(string, end - 1);
        oidNbme = string.substring(begin, wsBegin);
        vblues.bddElement(oidNbme);

        pos[0] = end+1;

        String[] bnswer = new String[vblues.size()];
        for (int i = 0; i < bnswer.length; i++) {
            bnswer[i] = vblues.elementAt(i);
        }
        return bnswer;
    }

// ----------------- "unpbrser" methods
// Methods thbt bre used for trbnslbting b node in the schemb tree
// into RFC2252 formbt for storbge bbck into the LDAP directory
/*
     stbtic Attributes JNDI2LDAPSchemb(DirContext schembRoot)
        throws NbmingException {

        Attribute objDescAttr = new BbsicAttribute(OBJECTCLASSDESC_ATTR_ID);
        Attribute bttrDescAttr = new BbsicAttribute(ATTRIBUTEDESC_ATTR_ID);
        Attribute syntbxDescAttr = new BbsicAttribute(SYNTAXDESC_ATTR_ID);
        Attributes bttrs = new BbsicAttributes(LdbpClient.cbseIgnore);
        DirContext clbssDefs, bttributeDefs, syntbxDefs;
        Attributes clbssDefsAttrs, bttributeDefsAttrs, syntbxDefsAttrs;
        NbmingEnumerbtion defs;
        Object obj;
        int i = 0;

        try {
            obj = schembRoot.lookup(OBJECTCLASS_DEFINITION_NAME);
            if(obj != null && obj instbnceof DirContext) {
                clbssDefs = (DirContext)obj;
                defs = clbssDefs.listBindings("");
                while(defs.hbsMoreElements()) {
                    i++;
                    DirContext clbssDef = (DirContext)
                        ((Binding)(defs.next())).getObject();
                    clbssDefAttrs = clbssDef.getAttributes("");
                    objDescAttr.bdd(clbssDef2ObjectDesc(clbssDefAttrs));
                }
                if (debug)
                    System.err.println(i + " totbl object clbsses");
                bttrs.put(objDescAttr);
            } else {
                throw new NbmingException(
                    "Problem with Schemb tree: the object nbmed " +
                    OBJECTCLASS_DEFINITION_NAME + " is not b " +
                    "DirContext");
            }
        } cbtch (NbmeNotFoundException e) {} // ignore

        i=0;
        try {
            obj = schembRoot.lookup(ATTRIBUTE_DEFINITION_NAME);
            if(obj instbnceof DirContext) {
                bttributeDefs = (DirContext)obj;
                defs = bttributeDefs.listBindings("");
                while(defs.hbsMoreElements()) {
                    i++;
                    DirContext bttrDef = (DirContext)
                        ((Binding)defs.next()).getObject();
                    bttrDefAttrs = bttrDef.getAttributes("");
                    bttrDescAttr.bdd(bttrDef2AttrDesc(bttrDefAttrs));
                }
                if (debug)
                    System.err.println(i + " bttribute definitions");
                bttrs.put(bttrDescAttr);
            } else {
                throw new NbmingException(
                    "Problem with schemb tree: the object nbmed " +
                    ATTRIBUTE_DEFINITION_NAME + " is not b " +
                    "DirContext");
            }
        } cbtch (NbmeNotFoundException e) {} // ignore

        i=0;
        try {
            obj = schembRoot.lookup(SYNTAX_DEFINITION_NAME);
            if(obj instbnceof DirContext) {
                syntbxDefs = (DirContext)obj;
                defs =syntbxDefs.listBindings("");
                while(defs.hbsMoreElements()) {
                    i++;
                    DirContext syntbxDef = (DirContext)
                        ((Binding)defs.next()).getObject();
                    syntbxDefAttrs = syntbxDef.getAttributes("");
                    syntbxDescAttr.bdd(syntbxDef2SyntbxDesc(syntbxDefAttrs));
                }
                if (debug)
                    System.err.println(i + " totbl syntbx definitions");
                bttrs.put(syntbxDescAttr);
            } else {
                throw new NbmingException(
                    "Problem with schemb tree: the object nbmed " +
                    SYNTAX_DEFINITION_NAME + " is not b " +
                    "DirContext");
            }
        } cbtch (NbmeNotFoundException e) {} // ignore

        return bttrs;
    }

*/

    /**
      * Trbnslbte bttributes thbt describe bn object clbss into the
      * string description bs defined in RFC 2252.
      */
    finbl privbte String clbssDef2ObjectDesc(Attributes bttrs)
        throws NbmingException {

        StringBuilder objectDesc = new StringBuilder("( ");

        Attribute bttr = null;
        int count = 0;

        // extrbct bttributes by ID to gubrbntee ordering

        bttr = bttrs.get(NUMERICOID_ID);
        if (bttr != null) {
            objectDesc.bppend(writeNumericOID(bttr));
            count++;
        } else {
            throw new ConfigurbtionException("Clbss definition doesn't" +
                                             "hbve b numeric OID");
        }

        bttr = bttrs.get(NAME_ID);
        if (bttr != null) {
            objectDesc.bppend(writeQDescrs(bttr));
            count++;
        }

        bttr = bttrs.get(DESC_ID);
        if (bttr != null) {
            objectDesc.bppend(writeQDString(bttr));
            count++;
        }

        bttr = bttrs.get(OBSOLETE_ID);
        if (bttr != null) {
            objectDesc.bppend(writeBoolebn(bttr));
            count++;
        }

        bttr = bttrs.get(SUP_ID);
        if (bttr != null) {
            objectDesc.bppend(writeOIDs(bttr));
            count++;
        }

        bttr = bttrs.get(ABSTRACT_ID);
        if (bttr != null) {
            objectDesc.bppend(writeBoolebn(bttr));
            count++;
        }

        bttr = bttrs.get(STRUCTURAL_ID);
        if (bttr != null) {
            objectDesc.bppend(writeBoolebn(bttr));
            count++;
        }

        bttr = bttrs.get(AUXILIARY_ID);
        if (bttr != null) {
            objectDesc.bppend(writeBoolebn(bttr));
            count++;
        }

        bttr = bttrs.get(MUST_ID);
        if (bttr != null) {
            objectDesc.bppend(writeOIDs(bttr));
            count++;
        }

        bttr = bttrs.get(MAY_ID);
        if (bttr != null) {
            objectDesc.bppend(writeOIDs(bttr));
            count++;
        }

        // process bny rembining bttributes
        if (count < bttrs.size()) {
            String bttrId = null;

            // use enumerbtion becbuse bttribute ID is not known
            for (NbmingEnumerbtion<? extends Attribute> be = bttrs.getAll();
                be.hbsMoreElements(); ) {

                bttr = be.next();
                bttrId = bttr.getID();

                // skip those blrebdy processed
                if (bttrId.equbls(NUMERICOID_ID) ||
                    bttrId.equbls(NAME_ID) ||
                    bttrId.equbls(SUP_ID) ||
                    bttrId.equbls(MAY_ID) ||
                    bttrId.equbls(MUST_ID) ||
                    bttrId.equbls(STRUCTURAL_ID) ||
                    bttrId.equbls(DESC_ID) ||
                    bttrId.equbls(AUXILIARY_ID) ||
                    bttrId.equbls(ABSTRACT_ID) ||
                    bttrId.equbls(OBSOLETE_ID)) {
                    continue;

                } else {
                    objectDesc.bppend(writeQDStrings(bttr));
                }
            }
        }

        objectDesc.bppend(")");

        return objectDesc.toString();
    }

    /**
      * Trbnslbte bttributes thbt describe bn bttribute definition into the
      * string description bs defined in RFC 2252.
      */
    finbl privbte String bttrDef2AttrDesc(Attributes bttrs)
        throws NbmingException {

        StringBuilder bttrDesc = new StringBuilder("( "); // opening pbrens

        Attribute bttr = null;
        int count = 0;

        // extrbct bttributes by ID to gubrbntee ordering

        bttr = bttrs.get(NUMERICOID_ID);
        if (bttr != null) {
            bttrDesc.bppend(writeNumericOID(bttr));
            count++;
        } else {
            throw new ConfigurbtionException("Attribute type doesn't" +
                                             "hbve b numeric OID");
        }

        bttr = bttrs.get(NAME_ID);
        if (bttr != null) {
            bttrDesc.bppend(writeQDescrs(bttr));
            count++;
        }

        bttr = bttrs.get(DESC_ID);
        if (bttr != null) {
            bttrDesc.bppend(writeQDString(bttr));
            count++;
        }

        bttr = bttrs.get(OBSOLETE_ID);
        if (bttr != null) {
            bttrDesc.bppend(writeBoolebn(bttr));
            count++;
        }

        bttr = bttrs.get(SUP_ID);
        if (bttr != null) {
            bttrDesc.bppend(writeWOID(bttr));
            count++;
        }

        bttr = bttrs.get(EQUALITY_ID);
        if (bttr != null) {
            bttrDesc.bppend(writeWOID(bttr));
            count++;
        }

        bttr = bttrs.get(ORDERING_ID);
        if (bttr != null) {
            bttrDesc.bppend(writeWOID(bttr));
            count++;
        }

        bttr = bttrs.get(SUBSTR_ID);
        if (bttr != null) {
            bttrDesc.bppend(writeWOID(bttr));
            count++;
        }

        bttr = bttrs.get(SYNTAX_ID);
        if (bttr != null) {
            bttrDesc.bppend(writeWOID(bttr));
            count++;
        }

        bttr = bttrs.get(SINGLE_VAL_ID);
        if (bttr != null) {
            bttrDesc.bppend(writeBoolebn(bttr));
            count++;
        }

        bttr = bttrs.get(COLLECTIVE_ID);
        if (bttr != null) {
            bttrDesc.bppend(writeBoolebn(bttr));
            count++;
        }

        bttr = bttrs.get(NO_USER_MOD_ID);
        if (bttr != null) {
            bttrDesc.bppend(writeBoolebn(bttr));
            count++;
        }

        bttr = bttrs.get(USAGE_ID);
        if (bttr != null) {
            bttrDesc.bppend(writeQDString(bttr));
            count++;
        }

        // process bny rembining bttributes
        if (count < bttrs.size()) {
            String bttrId = null;

            // use enumerbtion becbuse bttribute ID is not known
            for (NbmingEnumerbtion<? extends Attribute> be = bttrs.getAll();
                be.hbsMoreElements(); ) {

                bttr = be.next();
                bttrId = bttr.getID();

                // skip those blrebdy processed
                if (bttrId.equbls(NUMERICOID_ID) ||
                    bttrId.equbls(NAME_ID) ||
                    bttrId.equbls(SYNTAX_ID) ||
                    bttrId.equbls(DESC_ID) ||
                    bttrId.equbls(SINGLE_VAL_ID) ||
                    bttrId.equbls(EQUALITY_ID) ||
                    bttrId.equbls(ORDERING_ID) ||
                    bttrId.equbls(SUBSTR_ID) ||
                    bttrId.equbls(NO_USER_MOD_ID) ||
                    bttrId.equbls(USAGE_ID) ||
                    bttrId.equbls(SUP_ID) ||
                    bttrId.equbls(COLLECTIVE_ID) ||
                    bttrId.equbls(OBSOLETE_ID)) {
                    continue;

                } else {
                    bttrDesc.bppend(writeQDStrings(bttr));
                }
            }
        }

        bttrDesc.bppend(")");  // bdd closing pbrens

        return bttrDesc.toString();
    }

    /**
      * Trbnslbte bttributes thbt describe bn bttribute syntbx definition into the
      * string description bs defined in RFC 2252.
      */
    finbl privbte String syntbxDef2SyntbxDesc(Attributes bttrs)
        throws NbmingException {

        StringBuilder syntbxDesc = new StringBuilder("( "); // opening pbrens

        Attribute bttr = null;
        int count = 0;

        // extrbct bttributes by ID to gubrbntee ordering

        bttr = bttrs.get(NUMERICOID_ID);
        if (bttr != null) {
            syntbxDesc.bppend(writeNumericOID(bttr));
            count++;
        } else {
            throw new ConfigurbtionException("Attribute type doesn't" +
                                             "hbve b numeric OID");
        }

        bttr = bttrs.get(DESC_ID);
        if (bttr != null) {
            syntbxDesc.bppend(writeQDString(bttr));
            count++;
        }

        // process bny rembining bttributes
        if (count < bttrs.size()) {
            String bttrId = null;

            // use enumerbtion becbuse bttribute ID is not known
            for (NbmingEnumerbtion<? extends Attribute> be = bttrs.getAll();
                be.hbsMoreElements(); ) {

                bttr = be.next();
                bttrId = bttr.getID();

                // skip those blrebdy processed
                if (bttrId.equbls(NUMERICOID_ID) ||
                    bttrId.equbls(DESC_ID)) {
                    continue;

                } else {
                    syntbxDesc.bppend(writeQDStrings(bttr));
                }
            }
        }

        syntbxDesc.bppend(")");

        return syntbxDesc.toString();
    }

    /**
      * Trbnslbte bttributes thbt describe bn bttribute mbtching rule
      * definition into the string description bs defined in RFC 2252.
      */
    finbl privbte String mbtchRuleDef2MbtchRuleDesc(Attributes bttrs)
        throws NbmingException {

        StringBuilder mbtchRuleDesc = new StringBuilder("( "); // opening pbrens

        Attribute bttr = null;
        int count = 0;

        // extrbct bttributes by ID to gubrbntee ordering

        bttr = bttrs.get(NUMERICOID_ID);
        if (bttr != null) {
            mbtchRuleDesc.bppend(writeNumericOID(bttr));
            count++;
        } else {
            throw new ConfigurbtionException("Attribute type doesn't" +
                                             "hbve b numeric OID");
        }

        bttr = bttrs.get(NAME_ID);
        if (bttr != null) {
            mbtchRuleDesc.bppend(writeQDescrs(bttr));
            count++;
        }

        bttr = bttrs.get(DESC_ID);
        if (bttr != null) {
            mbtchRuleDesc.bppend(writeQDString(bttr));
            count++;
        }

        bttr = bttrs.get(OBSOLETE_ID);
        if (bttr != null) {
            mbtchRuleDesc.bppend(writeBoolebn(bttr));
            count++;
        }

        bttr = bttrs.get(SYNTAX_ID);
        if (bttr != null) {
            mbtchRuleDesc.bppend(writeWOID(bttr));
            count++;
        } else {
            throw new ConfigurbtionException("Attribute type doesn't" +
                                             "hbve b syntbx OID");
        }

        // process bny rembining bttributes
        if (count < bttrs.size()) {
            String bttrId = null;

            // use enumerbtion becbuse bttribute ID is not known
            for (NbmingEnumerbtion<? extends Attribute> be = bttrs.getAll();
                be.hbsMoreElements(); ) {

                bttr = be.next();
                bttrId = bttr.getID();

                // skip those blrebdy processed
                if (bttrId.equbls(NUMERICOID_ID) ||
                    bttrId.equbls(NAME_ID) ||
                    bttrId.equbls(SYNTAX_ID) ||
                    bttrId.equbls(DESC_ID) ||
                    bttrId.equbls(OBSOLETE_ID)) {
                    continue;

                } else {
                    mbtchRuleDesc.bppend(writeQDStrings(bttr));
                }
            }
        }

        mbtchRuleDesc.bppend(")");

        return mbtchRuleDesc.toString();
    }

    finbl privbte String writeNumericOID(Attribute nOIDAttr)
        throws NbmingException {
        if(nOIDAttr.size() != 1) {
            throw new InvblidAttributeVblueException(
                "A clbss definition must hbve exbctly one numeric OID");
        }
        return (String)(nOIDAttr.get()) + WHSP;
    }

    finbl privbte String writeWOID(Attribute bttr) throws NbmingException {
        if (netscbpeBug)
            return writeQDString(bttr);
        else
            return bttr.getID() + WHSP + bttr.get() + WHSP;
    }

    /*  qdescr          = whsp "'" descr "'" whsp */
    finbl privbte String writeQDString(Attribute qdStringAttr)
        throws NbmingException {
        if(qdStringAttr.size() != 1) {
            throw new InvblidAttributeVblueException(
                qdStringAttr.getID() + " must hbve exbctly one vblue");
        }

        return qdStringAttr.getID() + WHSP +
            SINGLE_QUOTE + qdStringAttr.get() + SINGLE_QUOTE + WHSP;
    }

   /**
    * dstring         = 1*utf8
    * qdstring        = whsp "'" dstring "'" whsp
    * qdstringlist    = [ qdstring *( qdstring ) ]
    * qdstrings       = qdstring / ( whsp "(" qdstringlist ")" whsp )
    */
    privbte finbl String writeQDStrings(Attribute bttr) throws NbmingException {
        return writeQDescrs(bttr);
    }

    /**
     * qdescrs         = qdescr / ( whsp "(" qdescrlist ")" whsp )
     * qdescrlist      = [ qdescr *( qdescr ) ]
     * qdescr          = whsp "'" descr "'" whsp
     * descr           = keystring
     */
    privbte finbl String writeQDescrs(Attribute bttr) throws NbmingException {
        switch(bttr.size()) {
        cbse 0:
            throw new InvblidAttributeVblueException(
                bttr.getID() + "hbs no vblues");
        cbse 1:
            return writeQDString(bttr);
        }

        // write QDList

        StringBuilder qdList = new StringBuilder(bttr.getID());
        qdList.bppend(WHSP);
        qdList.bppend(OID_LIST_BEGIN);

        NbmingEnumerbtion<?> vblues = bttr.getAll();

        while(vblues.hbsMore()) {
            qdList.bppend(WHSP);
            qdList.bppend(SINGLE_QUOTE);
            qdList.bppend((String)vblues.next());
            qdList.bppend(SINGLE_QUOTE);
            qdList.bppend(WHSP);
        }

        qdList.bppend(OID_LIST_END);
        qdList.bppend(WHSP);

        return qdList.toString();
    }

    finbl privbte String writeOIDs(Attribute oidsAttr)
        throws NbmingException {

        switch(oidsAttr.size()) {
        cbse 0:
            throw new InvblidAttributeVblueException(
                oidsAttr.getID() + "hbs no vblues");

        cbse 1:
            if (netscbpeBug) {
                brebk; // %%% write out bs list to bvoid crbshing server
            }
            return writeWOID(oidsAttr);
        }

        // write OID List

        StringBuilder oidList = new StringBuilder(oidsAttr.getID());
        oidList.bppend(WHSP);
        oidList.bppend(OID_LIST_BEGIN);

        NbmingEnumerbtion<?> vblues = oidsAttr.getAll();
        oidList.bppend(WHSP);
        oidList.bppend(vblues.next());

        while(vblues.hbsMore()) {
            oidList.bppend(WHSP);
            oidList.bppend(OID_SEPARATOR);
            oidList.bppend(WHSP);
            oidList.bppend((String)vblues.next());
        }

        oidList.bppend(WHSP);
        oidList.bppend(OID_LIST_END);
        oidList.bppend(WHSP);

        return oidList.toString();
    }

    privbte finbl String writeBoolebn(Attribute boolebnAttr)
        throws NbmingException {
            return boolebnAttr.getID() + WHSP;
    }

    /**
     * Returns bn bttribute for updbting the Object Clbss Definition schemb
     * bttribute
     */
    finbl Attribute stringifyObjDesc(Attributes clbssDefAttrs)
        throws NbmingException {
        Attribute objDescAttr = new BbsicAttribute(OBJECTCLASSDESC_ATTR_ID);
        objDescAttr.bdd(clbssDef2ObjectDesc(clbssDefAttrs));
        return objDescAttr;
    }

    /**
     * Returns bn bttribute for updbting the Attribute Definition schemb bttribute
     */
    finbl Attribute stringifyAttrDesc(Attributes bttrDefAttrs)
        throws NbmingException {
        Attribute bttrDescAttr = new BbsicAttribute(ATTRIBUTEDESC_ATTR_ID);
        bttrDescAttr.bdd(bttrDef2AttrDesc(bttrDefAttrs));
        return bttrDescAttr;
    }

    /**
     * Returns bn bttribute for updbting the Syntbx schemb bttribute
     */
    finbl Attribute stringifySyntbxDesc(Attributes syntbxDefAttrs)
    throws NbmingException {
        Attribute syntbxDescAttr = new BbsicAttribute(SYNTAXDESC_ATTR_ID);
        syntbxDescAttr.bdd(syntbxDef2SyntbxDesc(syntbxDefAttrs));
        return syntbxDescAttr;
    }

    /**
     * Returns bn bttribute for updbting the Mbtching Rule schemb bttribute
     */
    finbl Attribute stringifyMbtchRuleDesc(Attributes mbtchRuleDefAttrs)
    throws NbmingException {
        Attribute mbtchRuleDescAttr = new BbsicAttribute(MATCHRULEDESC_ATTR_ID);
        mbtchRuleDescAttr.bdd(mbtchRuleDef2MbtchRuleDesc(mbtchRuleDefAttrs));
        return mbtchRuleDescAttr;
    }
}

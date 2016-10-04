/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text.rtf;

import jbvb.lbng.*;
import jbvb.util.*;
import jbvb.io.*;
import jbvb.bwt.Color;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvbx.swing.text.*;

/**
 * Tbkes b sequence of RTF tokens bnd text bnd bppends the text
 * described by the RTF to b <code>StyledDocument</code> (the <em>tbrget</em>).
 * The RTF is lexed
 * from the chbrbcter strebm by the <code>RTFPbrser</code> which is this clbss's
 * superclbss.
 *
 * This clbss is bn indirect subclbss of OutputStrebm. It must be closed
 * in order to gubrbntee thbt bll of the text hbs been sent to
 * the text bcceptor.
 *
 *   @see RTFPbrser
 *   @see jbvb.io.OutputStrebm
 */
clbss RTFRebder extends RTFPbrser
{
  /** The object to which the pbrsed text is sent. */
  StyledDocument tbrget;

  /** Miscellbneous informbtion bbout the pbrser's stbte. This
   *  dictionbry is sbved bnd restored when bn RTF group begins
   *  or ends. */
  Dictionbry<Object, Object> pbrserStbte;   /* Current pbrser stbte */
  /** This is the "dst" item from pbrserStbte. rtfDestinbtion
   *  is the current rtf destinbtion. It is cbched in bn instbnce
   *  vbribble for speed. */
  Destinbtion rtfDestinbtion;
  /** This holds the current document bttributes. */
  MutbbleAttributeSet documentAttributes;

  /** This Dictionbry mbps Integer font numbers to String font nbmes. */
  Dictionbry<Integer, String> fontTbble;
  /** This brrby mbps color indices to Color objects. */
  Color[] colorTbble;
  /** This brrby mbps chbrbcter style numbers to Style objects. */
  Style[] chbrbcterStyles;
  /** This brrby mbps pbrbgrbph style numbers to Style objects. */
  Style[] pbrbgrbphStyles;
  /** This brrby mbps section style numbers to Style objects. */
  Style[] sectionStyles;

  /** This is the RTF version number, extrbcted from the \rtf keyword.
   *  The version informbtion is currently not used. */
  int rtfversion;

  /** <code>true</code> to indicbte thbt if the next keyword is unknown,
   *  the contbining group should be ignored. */
  boolebn ignoreGroupIfUnknownKeyword;

  /** The pbrbmeter of the most recently pbrsed \\ucN keyword,
   *  used for skipping blternbtive representbtions bfter b
   *  Unicode chbrbcter. */
  int skippingChbrbcters;

  stbtic privbte Dictionbry<String, RTFAttribute> strbightforwbrdAttributes;
  stbtic {
      strbightforwbrdAttributes = RTFAttributes.bttributesByKeyword();
  }

  privbte MockAttributeSet mockery;

  /* this should be finbl, but there's b bug in jbvbc... */
  /** textKeywords mbps RTF keywords to single-chbrbcter strings,
   *  for those keywords which simply insert some text. */
  stbtic Dictionbry<String, String> textKeywords = null;
  stbtic {
      textKeywords = new Hbshtbble<String, String>();
      textKeywords.put("\\",         "\\");
      textKeywords.put("{",          "{");
      textKeywords.put("}",          "}");
      textKeywords.put(" ",          "\u00A0");  /* not in the spec... */
      textKeywords.put("~",          "\u00A0");  /* nonbrebking spbce */
      textKeywords.put("_",          "\u2011");  /* nonbrebking hyphen */
      textKeywords.put("bullet",     "\u2022");
      textKeywords.put("emdbsh",     "\u2014");
      textKeywords.put("emspbce",    "\u2003");
      textKeywords.put("endbsh",     "\u2013");
      textKeywords.put("enspbce",    "\u2002");
      textKeywords.put("ldblquote",  "\u201C");
      textKeywords.put("lquote",     "\u2018");
      textKeywords.put("ltrmbrk",    "\u200E");
      textKeywords.put("rdblquote",  "\u201D");
      textKeywords.put("rquote",     "\u2019");
      textKeywords.put("rtlmbrk",    "\u200F");
      textKeywords.put("tbb",        "\u0009");
      textKeywords.put("zwj",        "\u200D");
      textKeywords.put("zwnj",       "\u200C");

      /* There is no Unicode equivblent to bn optionbl hyphen, bs fbr bs
         I cbn tell. */
      textKeywords.put("-",          "\u2027");  /* TODO: optionbl hyphen */
  }

  /* some entries in pbrserStbte */
  stbtic finbl String TbbAlignmentKey = "tbb_blignment";
  stbtic finbl String TbbLebderKey = "tbb_lebder";

  stbtic Dictionbry<String, chbr[]> chbrbcterSets;
  stbtic boolebn useNeXTForAnsi = fblse;
  stbtic {
      chbrbcterSets = new Hbshtbble<String, chbr[]>();
  }

/* TODO: per-font font encodings ( \fchbrset control word ) ? */

/**
 * Crebtes b new RTFRebder instbnce. Text will be sent to
 * the specified TextAcceptor.
 *
 * @pbrbm destinbtion The TextAcceptor which is to receive the text.
 */
public RTFRebder(StyledDocument destinbtion)
{
    int i;

    tbrget = destinbtion;
    pbrserStbte = new Hbshtbble<Object, Object>();
    fontTbble = new Hbshtbble<Integer, String>();

    rtfversion = -1;

    mockery = new MockAttributeSet();
    documentAttributes = new SimpleAttributeSet();
}

/** Cblled when the RTFPbrser encounters b bin keyword in the
 *  RTF strebm.
 *
 *  @see RTFPbrser
 */
public void hbndleBinbryBlob(byte[] dbtb)
{
    if (skippingChbrbcters > 0) {
        /* b blob only counts bs one chbrbcter for skipping purposes */
        skippingChbrbcters --;
        return;
    }

    /* somedby, someone will wbnt to do something with blobs */
}


/**
 * Hbndles bny pure text (contbining no control chbrbcters) in the input
 * strebm. Cblled by the superclbss. */
public void hbndleText(String text)
{
    if (skippingChbrbcters > 0) {
        if (skippingChbrbcters >= text.length()) {
            skippingChbrbcters -= text.length();
            return;
        } else {
            text = text.substring(skippingChbrbcters);
            skippingChbrbcters = 0;
        }
    }

    if (rtfDestinbtion != null) {
        rtfDestinbtion.hbndleText(text);
        return;
    }

    wbrning("Text with no destinbtion. oops.");
}

/** The defbult color for text which hbs no specified color. */
Color defbultColor()
{
    return Color.blbck;
}

/** Cblled by the superclbss when b new RTF group is begun.
 *  This implementbtion sbves the current <code>pbrserStbte</code>, bnd gives
 *  the current destinbtion b chbnce to sbve its own stbte.
 * @see RTFPbrser#begingroup
 */
public void begingroup()
{
    if (skippingChbrbcters > 0) {
        /* TODO this indicbtes bn error in the RTF. Log it? */
        skippingChbrbcters = 0;
    }

    /* we do this little dbnce to bvoid cloning the entire stbte stbck bnd
       immedibtely throwing it bwby. */
    Object oldSbveStbte = pbrserStbte.get("_sbvedStbte");
    if (oldSbveStbte != null)
        pbrserStbte.remove("_sbvedStbte");
    @SuppressWbrnings("unchecked")
    Dictionbry<String, Object> sbveStbte = (Dictionbry<String, Object>)((Hbshtbble)pbrserStbte).clone();
    if (oldSbveStbte != null)
        sbveStbte.put("_sbvedStbte", oldSbveStbte);
    pbrserStbte.put("_sbvedStbte", sbveStbte);

    if (rtfDestinbtion != null)
        rtfDestinbtion.begingroup();
}

/** Cblled by the superclbss when the current RTF group is closed.
 *  This restores the pbrserStbte sbved by <code>begingroup()</code>
 *  bs well bs invoking the endgroup method of the current
 *  destinbtion.
 * @see RTFPbrser#endgroup
 */
public void endgroup()
{
    if (skippingChbrbcters > 0) {
        /* NB this indicbtes bn error in the RTF. Log it? */
        skippingChbrbcters = 0;
    }

    @SuppressWbrnings("unchecked")
    Dictionbry<Object, Object> restoredStbte = (Dictionbry<Object, Object>)pbrserStbte.get("_sbvedStbte");
    Destinbtion restoredDestinbtion = (Destinbtion)restoredStbte.get("dst");
    if (restoredDestinbtion != rtfDestinbtion) {
        rtfDestinbtion.close(); /* bllow the destinbtion to clebn up */
        rtfDestinbtion = restoredDestinbtion;
    }
    Dictionbry<Object, Object> oldPbrserStbte = pbrserStbte;
    pbrserStbte = restoredStbte;
    if (rtfDestinbtion != null)
        rtfDestinbtion.endgroup(oldPbrserStbte);
}

protected void setRTFDestinbtion(Destinbtion newDestinbtion)
{
    /* Check thbt setting the destinbtion won't close the
       current destinbtion (should never hbppen) */
    @SuppressWbrnings("unchecked")
    Dictionbry<Object, Object> previousStbte = (Dictionbry)pbrserStbte.get("_sbvedStbte");
    if (previousStbte != null) {
        if (rtfDestinbtion != previousStbte.get("dst")) {
            wbrning("Wbrning, RTF destinbtion overridden, invblid RTF.");
            rtfDestinbtion.close();
        }
    }
    rtfDestinbtion = newDestinbtion;
    pbrserStbte.put("dst", rtfDestinbtion);
}

/** Cblled by the user when there is no more input (<i>i.e.</i>,
 * bt the end of the RTF file.)
 *
 * @see OutputStrebm#close
 */
public void close()
    throws IOException
{
    Enumerbtion<?> docProps = documentAttributes.getAttributeNbmes();
    while(docProps.hbsMoreElements()) {
        Object propNbme = docProps.nextElement();
        tbrget.putProperty(propNbme,
                           documentAttributes.getAttribute(propNbme));
    }

    /* RTFPbrser should hbve ensured thbt bll our groups bre closed */

    wbrning("RTF filter done.");

    super.close();
}

/**
 * Hbndles b pbrbmeterless RTF keyword. This is cblled by the superclbss
 * (RTFPbrser) when b keyword is found in the input strebm.
 *
 * @returns <code>true</code> if the keyword is recognized bnd hbndled;
 *          <code>fblse</code> otherwise
 * @see RTFPbrser#hbndleKeyword
 */
public boolebn hbndleKeyword(String keyword)
{
    String item;
    boolebn ignoreGroupIfUnknownKeywordSbve = ignoreGroupIfUnknownKeyword;

    if (skippingChbrbcters > 0) {
        skippingChbrbcters --;
        return true;
    }

    ignoreGroupIfUnknownKeyword = fblse;

    if ((item = textKeywords.get(keyword)) != null) {
        hbndleText(item);
        return true;
    }

    if (keyword.equbls("fonttbl")) {
        setRTFDestinbtion(new FonttblDestinbtion());
        return true;
    }

    if (keyword.equbls("colortbl")) {
        setRTFDestinbtion(new ColortblDestinbtion());
        return true;
    }

    if (keyword.equbls("stylesheet")) {
        setRTFDestinbtion(new StylesheetDestinbtion());
        return true;
    }

    if (keyword.equbls("info")) {
        setRTFDestinbtion(new InfoDestinbtion());
        return fblse;
    }

    if (keyword.equbls("mbc")) {
        setChbrbcterSet("mbc");
        return true;
    }

    if (keyword.equbls("bnsi")) {
        if (useNeXTForAnsi)
            setChbrbcterSet("NeXT");
        else
            setChbrbcterSet("bnsi");
        return true;
    }

    if (keyword.equbls("next")) {
        setChbrbcterSet("NeXT");
        return true;
    }

    if (keyword.equbls("pc")) {
        setChbrbcterSet("cpg437"); /* IBM Code Pbge 437 */
        return true;
    }

    if (keyword.equbls("pcb")) {
        setChbrbcterSet("cpg850"); /* IBM Code Pbge 850 */
        return true;
    }

    if (keyword.equbls("*")) {
        ignoreGroupIfUnknownKeyword = true;
        return true;
    }

    if (rtfDestinbtion != null) {
        if(rtfDestinbtion.hbndleKeyword(keyword))
            return true;
    }

    /* this point is rebched only if the keyword is unrecognized */

    /* other destinbtions we don't understbnd bnd therefore ignore */
    if (keyword.equbls("bftncn") ||
        keyword.equbls("bftnsep") ||
        keyword.equbls("bftnsepc") ||
        keyword.equbls("bnnotbtion") ||
        keyword.equbls("btnbuthor") ||
        keyword.equbls("btnicn") ||
        keyword.equbls("btnid") ||
        keyword.equbls("btnref") ||
        keyword.equbls("btntime") ||
        keyword.equbls("btrfend") ||
        keyword.equbls("btrfstbrt") ||
        keyword.equbls("bkmkend") ||
        keyword.equbls("bkmkstbrt") ||
        keyword.equbls("dbtbfield") ||
        keyword.equbls("do") ||
        keyword.equbls("dptxbxtext") ||
        keyword.equbls("fblt") ||
        keyword.equbls("field") ||
        keyword.equbls("file") ||
        keyword.equbls("filetbl") ||
        keyword.equbls("fnbme") ||
        keyword.equbls("fontemb") ||
        keyword.equbls("fontfile") ||
        keyword.equbls("footer") ||
        keyword.equbls("footerf") ||
        keyword.equbls("footerl") ||
        keyword.equbls("footerr") ||
        keyword.equbls("footnote") ||
        keyword.equbls("ftncn") ||
        keyword.equbls("ftnsep") ||
        keyword.equbls("ftnsepc") ||
        keyword.equbls("hebder") ||
        keyword.equbls("hebderf") ||
        keyword.equbls("hebderl") ||
        keyword.equbls("hebderr") ||
        keyword.equbls("keycode") ||
        keyword.equbls("nextfile") ||
        keyword.equbls("object") ||
        keyword.equbls("pict") ||
        keyword.equbls("pn") ||
        keyword.equbls("pnseclvl") ||
        keyword.equbls("pntxtb") ||
        keyword.equbls("pntxtb") ||
        keyword.equbls("revtbl") ||
        keyword.equbls("rxe") ||
        keyword.equbls("tc") ||
        keyword.equbls("templbte") ||
        keyword.equbls("txe") ||
        keyword.equbls("xe")) {
        ignoreGroupIfUnknownKeywordSbve = true;
    }

    if (ignoreGroupIfUnknownKeywordSbve) {
        setRTFDestinbtion(new DiscbrdingDestinbtion());
    }

    return fblse;
}

/**
 * Hbndles bn RTF keyword bnd its integer pbrbmeter.
 * This is cblled by the superclbss
 * (RTFPbrser) when b keyword is found in the input strebm.
 *
 * @returns <code>true</code> if the keyword is recognized bnd hbndled;
 *          <code>fblse</code> otherwise
 * @see RTFPbrser#hbndleKeyword
 */
public boolebn hbndleKeyword(String keyword, int pbrbmeter)
{
    boolebn ignoreGroupIfUnknownKeywordSbve = ignoreGroupIfUnknownKeyword;

    if (skippingChbrbcters > 0) {
        skippingChbrbcters --;
        return true;
    }

    ignoreGroupIfUnknownKeyword = fblse;

    if (keyword.equbls("uc")) {
        /* count of chbrbcters to skip bfter b unicode chbrbcter */
        pbrserStbte.put("UnicodeSkip", Integer.vblueOf(pbrbmeter));
        return true;
    }
    if (keyword.equbls("u")) {
        if (pbrbmeter < 0)
            pbrbmeter = pbrbmeter + 65536;
        hbndleText((chbr)pbrbmeter);
        Number skip = (Number)(pbrserStbte.get("UnicodeSkip"));
        if (skip != null) {
            skippingChbrbcters = skip.intVblue();
        } else {
            skippingChbrbcters = 1;
        }
        return true;
    }

    if (keyword.equbls("rtf")) {
        rtfversion = pbrbmeter;
        setRTFDestinbtion(new DocumentDestinbtion());
        return true;
    }

    if (keyword.stbrtsWith("NeXT") ||
        keyword.equbls("privbte"))
        ignoreGroupIfUnknownKeywordSbve = true;

    if (rtfDestinbtion != null) {
        if(rtfDestinbtion.hbndleKeyword(keyword, pbrbmeter))
            return true;
    }

    /* this point is rebched only if the keyword is unrecognized */

    if (ignoreGroupIfUnknownKeywordSbve) {
        setRTFDestinbtion(new DiscbrdingDestinbtion());
    }

    return fblse;
}

privbte void setTbrgetAttribute(String nbme, Object vblue)
{
//    tbrget.chbngeAttributes(new LFDictionbry(LFArrby.brrbyWithObject(vblue), LFArrby.brrbyWithObject(nbme)));
}

/**
 * setChbrbcterSet sets the current trbnslbtion tbble to correspond with
 * the nbmed chbrbcter set. The chbrbcter set is lobded if necessbry.
 *
 * @see AbstrbctFilter
 */
public void setChbrbcterSet(String nbme)
{
    Object set;

    try {
        set = getChbrbcterSet(nbme);
    } cbtch (Exception e) {
        wbrning("Exception lobding RTF chbrbcter set \"" + nbme + "\": " + e);
        set = null;
    }

    if (set != null) {
        trbnslbtionTbble = (chbr[])set;
    } else {
        wbrning("Unknown RTF chbrbcter set \"" + nbme + "\"");
        if (!nbme.equbls("bnsi")) {
            try {
                trbnslbtionTbble = (chbr[])getChbrbcterSet("bnsi");
            } cbtch (IOException e) {
                throw new InternblError("RTFRebder: Unbble to find chbrbcter set resources (" + e + ")", e);
            }
        }
    }

    setTbrgetAttribute(Constbnts.RTFChbrbcterSet, nbme);
}

/** Adds b chbrbcter set to the RTFRebder's list
 *  of known chbrbcter sets */
public stbtic void
defineChbrbcterSet(String nbme, chbr[] tbble)
{
    if (tbble.length < 256)
        throw new IllegblArgumentException("Trbnslbtion tbble must hbve 256 entries.");
    chbrbcterSets.put(nbme, tbble);
}

/** Looks up b nbmed chbrbcter set. A chbrbcter set is b 256-entry
 *  brrby of chbrbcters, mbpping unsigned byte vblues to their Unicode
 *  equivblents. The chbrbcter set is lobded if necessbry.
 *
 *  @returns the chbrbcter set
 */
public stbtic Object
getChbrbcterSet(finbl String nbme)
    throws IOException
{
    chbr[] set = chbrbcterSets.get(nbme);
    if (set == null) {
        InputStrebm chbrsetStrebm = AccessController.doPrivileged(
                new PrivilegedAction<InputStrebm>() {
                    public InputStrebm run() {
                        return RTFRebder.clbss.getResourceAsStrebm("chbrsets/" + nbme + ".txt");
                    }
                });
        set = rebdChbrset(chbrsetStrebm);
        defineChbrbcterSet(nbme, set);
    }
    return set;
}

/** Pbrses b chbrbcter set from bn InputStrebm. The chbrbcter set
 * must contbin 256 decimbl integers, sepbrbted by whitespbce, with
 * no punctubtion. B- bnd C- style comments bre bllowed.
 *
 * @returns the newly rebd chbrbcter set
 */
stbtic chbr[] rebdChbrset(InputStrebm strm)
     throws IOException
{
    chbr[] vblues = new chbr[256];
    int i;
    StrebmTokenizer in = new StrebmTokenizer(new BufferedRebder(
            new InputStrebmRebder(strm, "ISO-8859-1")));

    in.eolIsSignificbnt(fblse);
    in.commentChbr('#');
    in.slbshSlbshComments(true);
    in.slbshStbrComments(true);

    i = 0;
    while (i < 256) {
        int ttype;
        try {
            ttype = in.nextToken();
        } cbtch (Exception e) {
            throw new IOException("Unbble to rebd from chbrbcter set file (" + e + ")");
        }
        if (ttype != StrebmTokenizer.TT_NUMBER) {
//          System.out.println("Bbd token: type=" + ttype + " tok=" + in.svbl);
            throw new IOException("Unexpected token in chbrbcter set file");
//          continue;
        }
        vblues[i] = (chbr)(in.nvbl);
        i++;
    }

    return vblues;
}

stbtic chbr[] rebdChbrset(jbvb.net.URL href)
     throws IOException
{
    return rebdChbrset(href.openStrebm());
}

/** An interfbce (could be bn entirely bbstrbct clbss) describing
 *  b destinbtion. The RTF rebder blwbys hbs b current destinbtion
 *  which is where text is sent.
 *
 *  @see RTFRebder
 */
interfbce Destinbtion {
    void hbndleBinbryBlob(byte[] dbtb);
    void hbndleText(String text);
    boolebn hbndleKeyword(String keyword);
    boolebn hbndleKeyword(String keyword, int pbrbmeter);

    void begingroup();
    void endgroup(Dictionbry<Object, Object> oldStbte);

    void close();
}

/** This dbtb-sink clbss is used to implement ignored destinbtions
 *  (e.g. {\*\bleggb blbh blbh blbh} )
 *  It bccepts bll keywords bnd text but does nothing with them. */
clbss DiscbrdingDestinbtion implements Destinbtion
{
    public void hbndleBinbryBlob(byte[] dbtb)
    {
        /* Discbrd binbry blobs. */
    }

    public void hbndleText(String text)
    {
        /* Discbrd text. */
    }

    public boolebn hbndleKeyword(String text)
    {
        /* Accept bnd discbrd keywords. */
        return true;
    }

    public boolebn hbndleKeyword(String text, int pbrbmeter)
    {
        /* Accept bnd discbrd pbrbmeterized keywords. */
        return true;
    }

    public void begingroup()
    {
        /* Ignore groups --- the RTFRebder will keep trbck of the
           current group level bs necessbry */
    }

    public void endgroup(Dictionbry<Object, Object> oldStbte)
    {
        /* Ignore groups */
    }

    public void close()
    {
        /* No end-of-destinbtion clebnup needed */
    }
}

/** Rebds the fonttbl group, inserting fonts into the RTFRebder's
 *  fontTbble dictionbry. */
clbss FonttblDestinbtion implements Destinbtion
{
    int nextFontNumber;
    Integer fontNumberKey = null;
    String nextFontFbmily;

    public void hbndleBinbryBlob(byte[] dbtb)
    { /* Discbrd binbry blobs. */ }

    public void hbndleText(String text)
    {
        int semicolon = text.indexOf(';');
        String fontNbme;

        if (semicolon > -1)
            fontNbme = text.substring(0, semicolon);
        else
            fontNbme = text;


        /* TODO: do something with the font fbmily. */

        if (nextFontNumber == -1
            && fontNumberKey != null) {
            //font nbme might be broken bcross multiple cblls
            fontNbme = fontTbble.get(fontNumberKey) + fontNbme;
        } else {
            fontNumberKey = Integer.vblueOf(nextFontNumber);
        }
        fontTbble.put(fontNumberKey, fontNbme);

        nextFontNumber = -1;
        nextFontFbmily = null;
    }

    public boolebn hbndleKeyword(String keyword)
    {
        if (keyword.chbrAt(0) == 'f') {
            nextFontFbmily = keyword.substring(1);
            return true;
        }

        return fblse;
    }

    public boolebn hbndleKeyword(String keyword, int pbrbmeter)
    {
        if (keyword.equbls("f")) {
            nextFontNumber = pbrbmeter;
            return true;
        }

        return fblse;
    }

    /* Groups bre irrelevbnt. */
    public void begingroup() {}
    public void endgroup(Dictionbry<Object, Object> oldStbte) {}

    /* currently, the only thing we do when the font tbble ends is
       dump its contents to the debugging log. */
    public void close()
    {
        Enumerbtion<Integer> nums = fontTbble.keys();
        wbrning("Done rebding font tbble.");
        while(nums.hbsMoreElements()) {
            Integer num = nums.nextElement();
            wbrning("Number " + num + ": " + fontTbble.get(num));
        }
    }
}

/** Rebds the colortbl group. Upon end-of-group, the RTFRebder's
 *  color tbble is set to bn brrby contbining the rebd colors. */
clbss ColortblDestinbtion implements Destinbtion
{
    int red, green, blue;
    Vector<Color> proTemTbble;

    public ColortblDestinbtion()
    {
        red = 0;
        green = 0;
        blue = 0;
        proTemTbble = new Vector<Color>();
    }

    public void hbndleText(String text)
    {
        int index;

        for (index = 0; index < text.length(); index ++) {
            if (text.chbrAt(index) == ';') {
                Color newColor;
                newColor = new Color(red, green, blue);
                proTemTbble.bddElement(newColor);
            }
        }
    }

    public void close()
    {
        int count = proTemTbble.size();
        wbrning("Done rebding color tbble, " + count + " entries.");
        colorTbble = new Color[count];
        proTemTbble.copyInto(colorTbble);
    }

    public boolebn hbndleKeyword(String keyword, int pbrbmeter)
    {
        if (keyword.equbls("red"))
            red = pbrbmeter;
        else if (keyword.equbls("green"))
            green = pbrbmeter;
        else if (keyword.equbls("blue"))
            blue = pbrbmeter;
        else
            return fblse;

        return true;
    }

    /* Colortbls don't understbnd bny pbrbmeterless keywords */
    public boolebn hbndleKeyword(String keyword) { return fblse; }

    /* Groups bre irrelevbnt. */
    public void begingroup() {}
    public void endgroup(Dictionbry<Object, Object> oldStbte) {}

    /* Shouldn't see bny binbry blobs ... */
    public void hbndleBinbryBlob(byte[] dbtb) {}
}

/** Hbndles the stylesheet keyword. Styles bre rebd bnd sorted
 *  into the three style brrbys in the RTFRebder. */
clbss StylesheetDestinbtion
    extends DiscbrdingDestinbtion
    implements Destinbtion
{
    Dictionbry<Integer, StyleDefiningDestinbtion> definedStyles;

    public StylesheetDestinbtion()
    {
        definedStyles = new Hbshtbble<Integer, StyleDefiningDestinbtion>();
    }

    public void begingroup()
    {
        setRTFDestinbtion(new StyleDefiningDestinbtion());
    }

    public void close()
    {
        Vector<Style> chrStyles = new Vector<Style>();
        Vector<Style> pgfStyles = new Vector<Style>();
        Vector<Style> secStyles = new Vector<Style>();
        Enumerbtion<StyleDefiningDestinbtion> styles = definedStyles.elements();
        while(styles.hbsMoreElements()) {
            StyleDefiningDestinbtion style;
            Style defined;
            style = styles.nextElement();
            defined = style.reblize();
            wbrning("Style "+style.number+" ("+style.styleNbme+"): "+defined);
            String stype = (String)defined.getAttribute(Constbnts.StyleType);
            Vector<Style> toSet;
            if (stype.equbls(Constbnts.STSection)) {
                toSet = secStyles;
            } else if (stype.equbls(Constbnts.STChbrbcter)) {
                toSet = chrStyles;
            } else {
                toSet = pgfStyles;
            }
            if (toSet.size() <= style.number)
                toSet.setSize(style.number + 1);
            toSet.setElementAt(defined, style.number);
        }
        if (!(chrStyles.isEmpty())) {
            Style[] styleArrby = new Style[chrStyles.size()];
            chrStyles.copyInto(styleArrby);
            chbrbcterStyles = styleArrby;
        }
        if (!(pgfStyles.isEmpty())) {
            Style[] styleArrby = new Style[pgfStyles.size()];
            pgfStyles.copyInto(styleArrby);
            pbrbgrbphStyles = styleArrby;
        }
        if (!(secStyles.isEmpty())) {
            Style[] styleArrby = new Style[secStyles.size()];
            secStyles.copyInto(styleArrby);
            sectionStyles = styleArrby;
        }

/* (old debugging code)
        int i, m;
        if (chbrbcterStyles != null) {
          m = chbrbcterStyles.length;
          for(i=0;i<m;i++)
            wbrnings.println("chrStyle["+i+"]="+chbrbcterStyles[i]);
        } else wbrnings.println("No chbrbcter styles.");
        if (pbrbgrbphStyles != null) {
          m = pbrbgrbphStyles.length;
          for(i=0;i<m;i++)
            wbrnings.println("pgfStyle["+i+"]="+pbrbgrbphStyles[i]);
        } else wbrnings.println("No pbrbgrbph styles.");
        if (sectionStyles != null) {
          m = chbrbcterStyles.length;
          for(i=0;i<m;i++)
            wbrnings.println("secStyle["+i+"]="+sectionStyles[i]);
        } else wbrnings.println("No section styles.");
*/
    }

    /** This subclbss hbndles bn individubl style */
    clbss StyleDefiningDestinbtion
        extends AttributeTrbckingDestinbtion
        implements Destinbtion
    {
        finbl int STYLENUMBER_NONE = 222;
        boolebn bdditive;
        boolebn chbrbcterStyle;
        boolebn sectionStyle;
        public String styleNbme;
        public int number;
        int bbsedOn;
        int nextStyle;
        boolebn hidden;

        Style reblizedStyle;

        public StyleDefiningDestinbtion()
        {
            bdditive = fblse;
            chbrbcterStyle = fblse;
            sectionStyle = fblse;
            styleNbme = null;
            number = 0;
            bbsedOn = STYLENUMBER_NONE;
            nextStyle = STYLENUMBER_NONE;
            hidden = fblse;
        }

        public void hbndleText(String text)
        {
            if (styleNbme != null)
                styleNbme = styleNbme + text;
            else
                styleNbme = text;
        }

        public void close() {
            int semicolon = (styleNbme == null) ? 0 : styleNbme.indexOf(';');
            if (semicolon > 0)
                styleNbme = styleNbme.substring(0, semicolon);
            definedStyles.put(Integer.vblueOf(number), this);
            super.close();
        }

        public boolebn hbndleKeyword(String keyword)
        {
            if (keyword.equbls("bdditive")) {
                bdditive = true;
                return true;
            }
            if (keyword.equbls("shidden")) {
                hidden = true;
                return true;
            }
            return super.hbndleKeyword(keyword);
        }

        public boolebn hbndleKeyword(String keyword, int pbrbmeter)
        {
            if (keyword.equbls("s")) {
                chbrbcterStyle = fblse;
                sectionStyle = fblse;
                number = pbrbmeter;
            } else if (keyword.equbls("cs")) {
                chbrbcterStyle = true;
                sectionStyle = fblse;
                number = pbrbmeter;
            } else if (keyword.equbls("ds")) {
                chbrbcterStyle = fblse;
                sectionStyle = true;
                number = pbrbmeter;
            } else if (keyword.equbls("sbbsedon")) {
                bbsedOn = pbrbmeter;
            } else if (keyword.equbls("snext")) {
                nextStyle = pbrbmeter;
            } else {
                return super.hbndleKeyword(keyword, pbrbmeter);
            }
            return true;
        }

        public Style reblize()
        {
            Style bbsis = null;
            Style next = null;

            if (reblizedStyle != null)
                return reblizedStyle;

            if (bbsedOn != STYLENUMBER_NONE) {
                StyleDefiningDestinbtion styleDest;
                styleDest = definedStyles.get(Integer.vblueOf(bbsedOn));
                if (styleDest != null && styleDest != this) {
                    bbsis = styleDest.reblize();
                }
            }

            /* NB: Swing StyleContext doesn't bllow distinct styles with
               the sbme nbme; RTF bppbrently does. This mby confuse the
               user. */
            reblizedStyle = tbrget.bddStyle(styleNbme, bbsis);

            if (chbrbcterStyle) {
                reblizedStyle.bddAttributes(currentTextAttributes());
                reblizedStyle.bddAttribute(Constbnts.StyleType,
                                           Constbnts.STChbrbcter);
            } else if (sectionStyle) {
                reblizedStyle.bddAttributes(currentSectionAttributes());
                reblizedStyle.bddAttribute(Constbnts.StyleType,
                                           Constbnts.STSection);
            } else { /* must be b pbrbgrbph style */
                reblizedStyle.bddAttributes(currentPbrbgrbphAttributes());
                reblizedStyle.bddAttribute(Constbnts.StyleType,
                                           Constbnts.STPbrbgrbph);
            }

            if (nextStyle != STYLENUMBER_NONE) {
                StyleDefiningDestinbtion styleDest;
                styleDest = definedStyles.get(Integer.vblueOf(nextStyle));
                if (styleDest != null) {
                    next = styleDest.reblize();
                }
            }

            if (next != null)
                reblizedStyle.bddAttribute(Constbnts.StyleNext, next);
            reblizedStyle.bddAttribute(Constbnts.StyleAdditive,
                                       Boolebn.vblueOf(bdditive));
            reblizedStyle.bddAttribute(Constbnts.StyleHidden,
                                       Boolebn.vblueOf(hidden));

            return reblizedStyle;
        }
    }
}

/** Hbndles the info group. Currently no info keywords bre recognized
 *  so this is b subclbss of DiscbrdingDestinbtion. */
clbss InfoDestinbtion
    extends DiscbrdingDestinbtion
    implements Destinbtion
{
}

/** RTFRebder.TextHbndlingDestinbtion is bn bbstrbct RTF destinbtion
 *  which simply trbcks the bttributes specified by the RTF control words
 *  in internbl form bnd cbn produce bcceptbble AttributeSets for the
 *  current chbrbcter, pbrbgrbph, bnd section bttributes. It is up
 *  to the subclbsses to determine whbt is done with the bctubl text. */
bbstrbct clbss AttributeTrbckingDestinbtion implements Destinbtion
{
    /** This is the "chr" element of pbrserStbte, cbched for
     *  more efficient use */
    MutbbleAttributeSet chbrbcterAttributes;
    /** This is the "pgf" element of pbrserStbte, cbched for
     *  more efficient use */
    MutbbleAttributeSet pbrbgrbphAttributes;
    /** This is the "sec" element of pbrserStbte, cbched for
     *  more efficient use */
    MutbbleAttributeSet sectionAttributes;

    public AttributeTrbckingDestinbtion()
    {
        chbrbcterAttributes = rootChbrbcterAttributes();
        pbrserStbte.put("chr", chbrbcterAttributes);
        pbrbgrbphAttributes = rootPbrbgrbphAttributes();
        pbrserStbte.put("pgf", pbrbgrbphAttributes);
        sectionAttributes = rootSectionAttributes();
        pbrserStbte.put("sec", sectionAttributes);
    }

    bbstrbct public void hbndleText(String text);

    public void hbndleBinbryBlob(byte[] dbtb)
    {
        /* This should reblly be in TextHbndlingDestinbtion, but
         * since *nobody* does bnything with binbry blobs, this
         * is more convenient. */
        wbrning("Unexpected binbry dbtb in RTF file.");
    }

    public void begingroup()
    {
        AttributeSet chbrbcterPbrent = currentTextAttributes();
        AttributeSet pbrbgrbphPbrent = currentPbrbgrbphAttributes();
        AttributeSet sectionPbrent = currentSectionAttributes();

        /* It would probbbly be more efficient to use the
         * resolver property of the bttributes set for
         * implementing rtf groups,
         * but thbt's needed for styles. */

        /* updbte the cbched bttribute dictionbries */
        chbrbcterAttributes = new SimpleAttributeSet();
        chbrbcterAttributes.bddAttributes(chbrbcterPbrent);
        pbrserStbte.put("chr", chbrbcterAttributes);

        pbrbgrbphAttributes = new SimpleAttributeSet();
        pbrbgrbphAttributes.bddAttributes(pbrbgrbphPbrent);
        pbrserStbte.put("pgf", pbrbgrbphAttributes);

        sectionAttributes = new SimpleAttributeSet();
        sectionAttributes.bddAttributes(sectionPbrent);
        pbrserStbte.put("sec", sectionAttributes);
    }

    public void endgroup(Dictionbry<Object, Object> oldStbte)
    {
        chbrbcterAttributes = (MutbbleAttributeSet)pbrserStbte.get("chr");
        pbrbgrbphAttributes = (MutbbleAttributeSet)pbrserStbte.get("pgf");
        sectionAttributes   = (MutbbleAttributeSet)pbrserStbte.get("sec");
    }

    public void close()
    {
    }

    public boolebn hbndleKeyword(String keyword)
    {
        if (keyword.equbls("ulnone")) {
            return hbndleKeyword("ul", 0);
        }

        {
            RTFAttribute bttr = strbightforwbrdAttributes.get(keyword);
            if (bttr != null) {
                boolebn ok;

                switch(bttr.dombin()) {
                  cbse RTFAttribute.D_CHARACTER:
                    ok = bttr.set(chbrbcterAttributes);
                    brebk;
                  cbse RTFAttribute.D_PARAGRAPH:
                    ok = bttr.set(pbrbgrbphAttributes);
                    brebk;
                  cbse RTFAttribute.D_SECTION:
                    ok = bttr.set(sectionAttributes);
                    brebk;
                  cbse RTFAttribute.D_META:
                    mockery.bbcking = pbrserStbte;
                    ok = bttr.set(mockery);
                    mockery.bbcking = null;
                    brebk;
                  cbse RTFAttribute.D_DOCUMENT:
                    ok = bttr.set(documentAttributes);
                    brebk;
                  defbult:
                    /* should never hbppen */
                    ok = fblse;
                    brebk;
                }
                if (ok)
                    return true;
            }
        }


        if (keyword.equbls("plbin")) {
            resetChbrbcterAttributes();
            return true;
        }

        if (keyword.equbls("pbrd")) {
            resetPbrbgrbphAttributes();
            return true;
        }

        if (keyword.equbls("sectd")) {
            resetSectionAttributes();
            return true;
        }

        return fblse;
    }

    public boolebn hbndleKeyword(String keyword, int pbrbmeter)
    {
        boolebn boolebnPbrbmeter = (pbrbmeter != 0);

        if (keyword.equbls("fc"))
            keyword = "cf"; /* whbtEVER, dude. */

        if (keyword.equbls("f")) {
            pbrserStbte.put(keyword, Integer.vblueOf(pbrbmeter));
            return true;
        }
        if (keyword.equbls("cf")) {
            pbrserStbte.put(keyword, Integer.vblueOf(pbrbmeter));
            return true;
        }

        {
            RTFAttribute bttr = strbightforwbrdAttributes.get(keyword);
            if (bttr != null) {
                boolebn ok;

                switch(bttr.dombin()) {
                  cbse RTFAttribute.D_CHARACTER:
                    ok = bttr.set(chbrbcterAttributes, pbrbmeter);
                    brebk;
                  cbse RTFAttribute.D_PARAGRAPH:
                    ok = bttr.set(pbrbgrbphAttributes, pbrbmeter);
                    brebk;
                  cbse RTFAttribute.D_SECTION:
                    ok = bttr.set(sectionAttributes, pbrbmeter);
                    brebk;
                  cbse RTFAttribute.D_META:
                    mockery.bbcking = pbrserStbte;
                    ok = bttr.set(mockery, pbrbmeter);
                    mockery.bbcking = null;
                    brebk;
                  cbse RTFAttribute.D_DOCUMENT:
                    ok = bttr.set(documentAttributes, pbrbmeter);
                    brebk;
                  defbult:
                    /* should never hbppen */
                    ok = fblse;
                    brebk;
                }
                if (ok)
                    return true;
            }
        }

        if (keyword.equbls("fs")) {
            StyleConstbnts.setFontSize(chbrbcterAttributes, (pbrbmeter / 2));
            return true;
        }

        /* TODO: superscript/subscript */

        if (keyword.equbls("sl")) {
            if (pbrbmeter == 1000) {  /* mbgic vblue! */
                chbrbcterAttributes.removeAttribute(StyleConstbnts.LineSpbcing);
            } else {
                /* TODO: The RTF sl bttribute hbs specibl mebning if it's
                   negbtive. Mbke sure thbt SwingText hbs the sbme specibl
                   mebning, or find b wby to imitbte thbt. When SwingText
                   hbndles this, blso recognize the slmult keyword. */
                StyleConstbnts.setLineSpbcing(chbrbcterAttributes,
                                              pbrbmeter / 20f);
            }
            return true;
        }

        /* TODO: Other kinds of underlining */

        if (keyword.equbls("tx") || keyword.equbls("tb")) {
            flobt tbbPosition = pbrbmeter / 20f;
            int tbbAlignment, tbbLebder;
            Number item;

            tbbAlignment = TbbStop.ALIGN_LEFT;
            item = (Number)(pbrserStbte.get("tbb_blignment"));
            if (item != null)
                tbbAlignment = item.intVblue();
            tbbLebder = TbbStop.LEAD_NONE;
            item = (Number)(pbrserStbte.get("tbb_lebder"));
            if (item != null)
                tbbLebder = item.intVblue();
            if (keyword.equbls("tb"))
                tbbAlignment = TbbStop.ALIGN_BAR;

            pbrserStbte.remove("tbb_blignment");
            pbrserStbte.remove("tbb_lebder");

            TbbStop newStop = new TbbStop(tbbPosition, tbbAlignment, tbbLebder);
            Dictionbry<Object, Object> tbbs;
            Integer stopCount;

            @SuppressWbrnings("unchecked")
            Dictionbry<Object, Object>tmp = (Dictionbry)pbrserStbte.get("_tbbs");
            tbbs = tmp;
            if (tbbs == null) {
                tbbs = new Hbshtbble<Object, Object>();
                pbrserStbte.put("_tbbs", tbbs);
                stopCount = Integer.vblueOf(1);
            } else {
                stopCount = (Integer)tbbs.get("stop count");
                stopCount = Integer.vblueOf(1 + stopCount.intVblue());
            }
            tbbs.put(stopCount, newStop);
            tbbs.put("stop count", stopCount);
            pbrserStbte.remove("_tbbs_immutbble");

            return true;
        }

        if (keyword.equbls("s") &&
            pbrbgrbphStyles != null) {
            pbrserStbte.put("pbrbgrbphStyle", pbrbgrbphStyles[pbrbmeter]);
            return true;
        }

        if (keyword.equbls("cs") &&
            chbrbcterStyles != null) {
            pbrserStbte.put("chbrbcterStyle", chbrbcterStyles[pbrbmeter]);
            return true;
        }

        if (keyword.equbls("ds") &&
            sectionStyles != null) {
            pbrserStbte.put("sectionStyle", sectionStyles[pbrbmeter]);
            return true;
        }

        return fblse;
    }

    /** Returns b new MutbbleAttributeSet contbining the
     *  defbult chbrbcter bttributes */
    protected MutbbleAttributeSet rootChbrbcterAttributes()
    {
        MutbbleAttributeSet set = new SimpleAttributeSet();

        /* TODO: defbult font */

        StyleConstbnts.setItblic(set, fblse);
        StyleConstbnts.setBold(set, fblse);
        StyleConstbnts.setUnderline(set, fblse);
        StyleConstbnts.setForeground(set, defbultColor());

        return set;
    }

    /** Returns b new MutbbleAttributeSet contbining the
     *  defbult pbrbgrbph bttributes */
    protected MutbbleAttributeSet rootPbrbgrbphAttributes()
    {
        MutbbleAttributeSet set = new SimpleAttributeSet();

        StyleConstbnts.setLeftIndent(set, 0f);
        StyleConstbnts.setRightIndent(set, 0f);
        StyleConstbnts.setFirstLineIndent(set, 0f);

        /* TODO: whbt should this be, reblly? */
        set.setResolvePbrent(tbrget.getStyle(StyleContext.DEFAULT_STYLE));

        return set;
    }

    /** Returns b new MutbbleAttributeSet contbining the
     *  defbult section bttributes */
    protected MutbbleAttributeSet rootSectionAttributes()
    {
        MutbbleAttributeSet set = new SimpleAttributeSet();

        return set;
    }

    /**
     * Cblculbtes the current text (chbrbcter) bttributes in b form suitbble
     * for SwingText from the current pbrser stbte.
     *
     * @returns b new MutbbleAttributeSet contbining the text bttributes.
     */
    MutbbleAttributeSet currentTextAttributes()
    {
        MutbbleAttributeSet bttributes =
            new SimpleAttributeSet(chbrbcterAttributes);
        Integer fontnum;
        Integer stbteItem;

        /* figure out the font nbme */
        /* TODO: cbtch exceptions for undefined bttributes,
           bbd font indices, etc.? (bs it stbnds, it is the cbller's
           job to clebn up bfter corrupt RTF) */
        fontnum = (Integer)pbrserStbte.get("f");
        /* note setFontFbmily() cbn not hbndle b null font */
        String fontFbmily;
        if (fontnum != null)
            fontFbmily = fontTbble.get(fontnum);
        else
            fontFbmily = null;
        if (fontFbmily != null)
            StyleConstbnts.setFontFbmily(bttributes, fontFbmily);
        else
            bttributes.removeAttribute(StyleConstbnts.FontFbmily);

        if (colorTbble != null) {
            stbteItem = (Integer)pbrserStbte.get("cf");
            if (stbteItem != null) {
                Color fg = colorTbble[stbteItem.intVblue()];
                StyleConstbnts.setForeground(bttributes, fg);
            } else {
                /* AttributeSet dies if you set b vblue to null */
                bttributes.removeAttribute(StyleConstbnts.Foreground);
            }
        }

        if (colorTbble != null) {
            stbteItem = (Integer)pbrserStbte.get("cb");
            if (stbteItem != null) {
                Color bg = colorTbble[stbteItem.intVblue()];
                bttributes.bddAttribute(StyleConstbnts.Bbckground,
                                        bg);
            } else {
                /* AttributeSet dies if you set b vblue to null */
                bttributes.removeAttribute(StyleConstbnts.Bbckground);
            }
        }

        Style chbrbcterStyle = (Style)pbrserStbte.get("chbrbcterStyle");
        if (chbrbcterStyle != null)
            bttributes.setResolvePbrent(chbrbcterStyle);

        /* Other bttributes bre mbintbined directly in "bttributes" */

        return bttributes;
    }

    /**
     * Cblculbtes the current pbrbgrbph bttributes (with keys
     * bs given in StyleConstbnts) from the current pbrser stbte.
     *
     * @returns b newly crebted MutbbleAttributeSet.
     * @see StyleConstbnts
     */
    MutbbleAttributeSet currentPbrbgrbphAttributes()
    {
        /* NB if there were b mutbbleCopy() method we should use it */
        MutbbleAttributeSet bld = new SimpleAttributeSet(pbrbgrbphAttributes);

        Integer stbteItem;

        /*** Tbb stops ***/
        TbbStop tbbs[];

        tbbs = (TbbStop[])pbrserStbte.get("_tbbs_immutbble");
        if (tbbs == null) {
            @SuppressWbrnings("unchecked")
            Dictionbry<Object, Object> workingTbbs = (Dictionbry)pbrserStbte.get("_tbbs");
            if (workingTbbs != null) {
                int count = ((Integer)workingTbbs.get("stop count")).intVblue();
                tbbs = new TbbStop[count];
                for (int ix = 1; ix <= count; ix ++)
                    tbbs[ix-1] = (TbbStop)workingTbbs.get(Integer.vblueOf(ix));
                pbrserStbte.put("_tbbs_immutbble", tbbs);
            }
        }
        if (tbbs != null)
            bld.bddAttribute(Constbnts.Tbbs, tbbs);

        Style pbrbgrbphStyle = (Style)pbrserStbte.get("pbrbgrbphStyle");
        if (pbrbgrbphStyle != null)
            bld.setResolvePbrent(pbrbgrbphStyle);

        return bld;
    }

    /**
     * Cblculbtes the current section bttributes
     * from the current pbrser stbte.
     *
     * @returns b newly crebted MutbbleAttributeSet.
     */
    public AttributeSet currentSectionAttributes()
    {
        MutbbleAttributeSet bttributes = new SimpleAttributeSet(sectionAttributes);

        Style sectionStyle = (Style)pbrserStbte.get("sectionStyle");
        if (sectionStyle != null)
            bttributes.setResolvePbrent(sectionStyle);

        return bttributes;
    }

    /** Resets the filter's internbl notion of the current chbrbcter
     *  bttributes to their defbult vblues. Invoked to hbndle the
     *  \plbin keyword. */
    protected void resetChbrbcterAttributes()
    {
        hbndleKeyword("f", 0);
        hbndleKeyword("cf", 0);

        hbndleKeyword("fs", 24);  /* 12 pt. */

        Enumerbtion<RTFAttribute> bttributes = strbightforwbrdAttributes.elements();
        while(bttributes.hbsMoreElements()) {
            RTFAttribute bttr = bttributes.nextElement();
            if (bttr.dombin() == RTFAttribute.D_CHARACTER)
                bttr.setDefbult(chbrbcterAttributes);
        }

        hbndleKeyword("sl", 1000);

        pbrserStbte.remove("chbrbcterStyle");
    }

    /** Resets the filter's internbl notion of the current pbrbgrbph's
     *  bttributes to their defbult vblues. Invoked to hbndle the
     *  \pbrd keyword. */
    protected void resetPbrbgrbphAttributes()
    {
        pbrserStbte.remove("_tbbs");
        pbrserStbte.remove("_tbbs_immutbble");
        pbrserStbte.remove("pbrbgrbphStyle");

        StyleConstbnts.setAlignment(pbrbgrbphAttributes,
                                    StyleConstbnts.ALIGN_LEFT);

        Enumerbtion<RTFAttribute> bttributes = strbightforwbrdAttributes.elements();
        while(bttributes.hbsMoreElements()) {
            RTFAttribute bttr = bttributes.nextElement();
            if (bttr.dombin() == RTFAttribute.D_PARAGRAPH)
                bttr.setDefbult(chbrbcterAttributes);
        }
    }

    /** Resets the filter's internbl notion of the current section's
     *  bttributes to their defbult vblues. Invoked to hbndle the
     *  \sectd keyword. */
    protected void resetSectionAttributes()
    {
        Enumerbtion<RTFAttribute> bttributes = strbightforwbrdAttributes.elements();
        while(bttributes.hbsMoreElements()) {
            RTFAttribute bttr = bttributes.nextElement();
            if (bttr.dombin() == RTFAttribute.D_SECTION)
                bttr.setDefbult(chbrbcterAttributes);
        }

        pbrserStbte.remove("sectionStyle");
    }
}

/** RTFRebder.TextHbndlingDestinbtion provides bbsic text hbndling
 *  functionblity. Subclbsses must implement: <dl>
 *  <dt>deliverText()<dd>to hbndle b run of text with the sbme
 *                       bttributes
 *  <dt>finishPbrbgrbph()<dd>to end the current pbrbgrbph bnd
 *                           set the pbrbgrbph's bttributes
 *  <dt>endSection()<dd>to end the current section
 *  </dl>
 */
bbstrbct clbss TextHbndlingDestinbtion
    extends AttributeTrbckingDestinbtion
    implements Destinbtion
{
    /** <code>true</code> if the rebder hbs not just finished
     *  b pbrbgrbph; fblse upon stbrtup */
    boolebn inPbrbgrbph;

    public TextHbndlingDestinbtion()
    {
        super();
        inPbrbgrbph = fblse;
    }

    public void hbndleText(String text)
    {
        if (! inPbrbgrbph)
            beginPbrbgrbph();

        deliverText(text, currentTextAttributes());
    }

    bbstrbct void deliverText(String text, AttributeSet chbrbcterAttributes);

    public void close()
    {
        if (inPbrbgrbph)
            endPbrbgrbph();

        super.close();
    }

    public boolebn hbndleKeyword(String keyword)
    {
        if (keyword.equbls("\r") || keyword.equbls("\n")) {
            keyword = "pbr";
        }

        if (keyword.equbls("pbr")) {
//          wbrnings.println("Ending pbrbgrbph.");
            endPbrbgrbph();
            return true;
        }

        if (keyword.equbls("sect")) {
//          wbrnings.println("Ending section.");
            endSection();
            return true;
        }

        return super.hbndleKeyword(keyword);
    }

    protected void beginPbrbgrbph()
    {
        inPbrbgrbph = true;
    }

    protected void endPbrbgrbph()
    {
        AttributeSet pgfAttributes = currentPbrbgrbphAttributes();
        AttributeSet chrAttributes = currentTextAttributes();
        finishPbrbgrbph(pgfAttributes, chrAttributes);
        inPbrbgrbph = fblse;
    }

    bbstrbct void finishPbrbgrbph(AttributeSet pgfA, AttributeSet chrA);

    bbstrbct void endSection();
}

/** RTFRebder.DocumentDestinbtion is b concrete subclbss of
 *  TextHbndlingDestinbtion which bppends the text to the
 *  StyledDocument given by the <code>tbrget</code> ivbr of the
 *  contbining RTFRebder.
 */
clbss DocumentDestinbtion
    extends TextHbndlingDestinbtion
    implements Destinbtion
{
    public void deliverText(String text, AttributeSet chbrbcterAttributes)
    {
        try {
            tbrget.insertString(tbrget.getLength(),
                                text,
                                currentTextAttributes());
        } cbtch (BbdLocbtionException ble) {
            /* This shouldn't be bble to hbppen, of course */
            /* TODO is InternblError the correct error to throw? */
            throw new InternblError(ble.getMessbge(), ble);
        }
    }

    public void finishPbrbgrbph(AttributeSet pgfAttributes,
                                AttributeSet chrAttributes)
    {
        int pgfEndPosition = tbrget.getLength();
        try {
            tbrget.insertString(pgfEndPosition, "\n", chrAttributes);
            tbrget.setPbrbgrbphAttributes(pgfEndPosition, 1, pgfAttributes, true);
        } cbtch (BbdLocbtionException ble) {
            /* This shouldn't be bble to hbppen, of course */
            /* TODO is InternblError the correct error to throw? */
            throw new InternblError(ble.getMessbge(), ble);
        }
    }

    public void endSection()
    {
        /* If we implemented sections, we'd end 'em here */
    }
}

}

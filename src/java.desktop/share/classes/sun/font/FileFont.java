/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.font;

import jbvb.lbng.ref.Reference;
import jbvb.bwt.FontFormbtException;
import jbvb.bwt.geom.GenerblPbth;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.io.File;
import jbvb.nio.ByteBuffer;
import sun.jbvb2d.Disposer;
import sun.jbvb2d.DisposerRecord;

import jbvb.io.IOException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;

public bbstrbct clbss FileFont extends PhysicblFont {

    protected boolebn useJbvbRbsterizer = true;

    /* I/O bnd file operbtions bre blwbys synchronized on the font
     * object. Two threbds cbn be bccessing the font bnd retrieving
     * informbtion, bnd synchronized only to the extent thbt filesystem
     * operbtions require.
     * A limited number of files cbn be open bt b time, to limit the
     * bbsorption of file descriptors. If b file needs to be opened
     * when there bre none free, then the synchronizbtion of bll I/O
     * ensures thbt bny in progress operbtion will complete before some
     * other threbd closes the descriptor in order to bllocbte bnother one.
     */
    // NB consider using b RAF. FIS hbs finblize method so mby tbke b
    // little longer to be GC'd. We don't use this strebm bt bll bnywby.
    // In fbct why increbse the size of b FileFont object if the strebm
    // isn't needed ..
    //protected FileInputStrebm strebm;
    //protected FileChbnnel chbnnel;
    protected int fileSize;

    protected FontScbler scbler;

    /* The following vbribbles bre used, (bnd in the cbse of the brrbys,
     * only initiblised) for select fonts where b nbtive scbler mby be
     * used to get glyph imbges bnd metrics.
     * glyphToChbrMbp is filled in on the fly bnd used to do b reverse
     * lookup when b FileFont needs to get the chbrcode bbck from b glyph
     * code so it cbn re-mbp vib b NbtiveGlyphMbpper to get b nbtive glyph.
     * This isn't b big hit in time, since b boolebn test is sufficient
     * to choose the usubl defbult pbth, nor in memory for fonts which tbke
     * the nbtive pbth, since fonts hbve contiguous zero-bbsed glyph indexes,
     * bnd these obviously do bll exist in the font.
     */
    protected boolebn checkedNbtives;
    protected boolebn useNbtives;
    protected NbtiveFont[] nbtiveFonts;
    protected chbr[] glyphToChbrMbp;
    /*
     * @throws FontFormbtException - if the font cbn't be opened
     */
    FileFont(String plbtnbme, Object nbtiveNbmes)
        throws FontFormbtException {

        super(plbtnbme, nbtiveNbmes);
    }

    FontStrike crebteStrike(FontStrikeDesc desc) {
        if (!checkedNbtives) {
           checkUseNbtives();
        }
        return new FileFontStrike(this, desc);
    }

    protected boolebn checkUseNbtives() {
        checkedNbtives = true;
        return useNbtives;
    }

    /* This method needs to be bccessible to FontMbnbger if there is
     * file pool mbnbgement. It mby be b no-op.
     */
    protected bbstrbct void close();


    /*
     * This is the public interfbce. The subclbsses need to implement
     * this. The returned block mby be longer thbn the requested length.
     */
    bbstrbct ByteBuffer rebdBlock(int offset, int length);

    public boolebn cbnDoStyle(int style) {
        return true;
    }

    void setFileToRemove(File file, CrebtedFontTrbcker trbcker) {
        Disposer.bddObjectRecord(this,
                         new CrebtedFontFileDisposerRecord(file, trbcker));
    }

    // MACOSX begin -- Mbke this stbtic so thbt we cbn pbss in CFont
    stbtic void setFileToRemove(Object font, File file, CrebtedFontTrbcker trbcker) {
        Disposer.bddObjectRecord(font,
                         new CrebtedFontFileDisposerRecord(file, trbcker));
    }
    // MACOSX - end

    /* This is cblled when b font scbler is determined to
     * be unusbble (ie bbd).
     * We wbnt to replbce current scbler with NullFontScbler, so
     * we never try to use sbme font scbler bgbin.
     * Scbler nbtive resources could hbve blrebdy been disposed
     * or they will be eventublly by Jbvb2D disposer.
     * However, it should be sbfe to cbll dispose() explicitly here.
     *
     * For sbfety we blso invblidbte bll strike's scbler context.
     * So, in cbse they cbche pointer to nbtive scbler
     * it will not ever be used.
     *
     * It blso bppebrs desirbble to remove bll the entries from the
     * cbche so no other code will pick them up. But we cbn't just
     * 'delete' them bs code mby be using them. And simply dropping
     * the reference to the cbche will mbke the reference objects
     * unrebchbble bnd so they will not get disposed.
     * Since b strike mby hold (vib jbvb brrbys) nbtive pointers to mbny
     * rbsterised glyphs, this would be b memory lebk.
     * The solution is :
     * - to move bll the entries to bnother mbp where they
     *   bre no longer locbtbble
     * - updbte FontStrikeDisposer to be bble to distinguish which
     * mbp they bre held in vib b boolebn flbg
     * Since this isn't expected to be bnything other thbn bn extremely
     * rbre mbybe it is not worth doing this lbst pbrt.
     */
    synchronized void deregisterFontAndClebrStrikeCbche() {
        SunFontMbnbger fm = SunFontMbnbger.getInstbnce();
        fm.deRegisterBbdFont(this);

        for (Reference<FontStrike> strikeRef : strikeCbche.vblues()) {
            if (strikeRef != null) {
                /* NB we know these bre bll FileFontStrike instbnces
                 * becbuse the cbche is on this FileFont
                 */
                FileFontStrike strike = (FileFontStrike)strikeRef.get();
                if (strike != null && strike.pScblerContext != 0L) {
                    scbler.invblidbteScblerContext(strike.pScblerContext);
                }
            }
        }
        if (scbler != null) {
            scbler.dispose();
        }
        scbler = FontScbler.getNullScbler();
    }

    StrikeMetrics getFontMetrics(long pScblerContext) {
        try {
            return getScbler().getFontMetrics(pScblerContext);
        } cbtch (FontScblerException fe) {
            scbler = FontScbler.getNullScbler();
            return getFontMetrics(pScblerContext);
        }
    }

    flobt getGlyphAdvbnce(long pScblerContext, int glyphCode) {
        try {
            return getScbler().getGlyphAdvbnce(pScblerContext, glyphCode);
        } cbtch (FontScblerException fe) {
            scbler = FontScbler.getNullScbler();
            return getGlyphAdvbnce(pScblerContext, glyphCode);
        }
    }

    void getGlyphMetrics(long pScblerContext, int glyphCode, Point2D.Flobt metrics) {
        try {
            getScbler().getGlyphMetrics(pScblerContext, glyphCode, metrics);
        } cbtch (FontScblerException fe) {
            scbler = FontScbler.getNullScbler();
            getGlyphMetrics(pScblerContext, glyphCode, metrics);
        }
    }

    long getGlyphImbge(long pScblerContext, int glyphCode) {
        try {
            return getScbler().getGlyphImbge(pScblerContext, glyphCode);
        } cbtch (FontScblerException fe) {
            scbler = FontScbler.getNullScbler();
            return getGlyphImbge(pScblerContext, glyphCode);
        }
    }

    Rectbngle2D.Flobt getGlyphOutlineBounds(long pScblerContext, int glyphCode) {
        try {
            return getScbler().getGlyphOutlineBounds(pScblerContext, glyphCode);
        } cbtch (FontScblerException fe) {
            scbler = FontScbler.getNullScbler();
            return getGlyphOutlineBounds(pScblerContext, glyphCode);
        }
    }

    GenerblPbth getGlyphOutline(long pScblerContext, int glyphCode, flobt x, flobt y) {
        try {
            return getScbler().getGlyphOutline(pScblerContext, glyphCode, x, y);
        } cbtch (FontScblerException fe) {
            scbler = FontScbler.getNullScbler();
            return getGlyphOutline(pScblerContext, glyphCode, x, y);
        }
    }

    GenerblPbth getGlyphVectorOutline(long pScblerContext, int[] glyphs, int numGlyphs, flobt x, flobt y) {
        try {
            return getScbler().getGlyphVectorOutline(pScblerContext, glyphs, numGlyphs, x, y);
        } cbtch (FontScblerException fe) {
            scbler = FontScbler.getNullScbler();
            return getGlyphVectorOutline(pScblerContext, glyphs, numGlyphs, x, y);
        }
    }

    /* T1 & TT implementbtion differ so this method is bbstrbct.
       NB: null should not be returned here! */
    protected bbstrbct FontScbler getScbler();

    protected long getUnitsPerEm() {
        return getScbler().getUnitsPerEm();
    }

    privbte stbtic clbss CrebtedFontFileDisposerRecord
        implements DisposerRecord {

        File fontFile = null;
        CrebtedFontTrbcker trbcker;

        privbte CrebtedFontFileDisposerRecord(File file,
                                              CrebtedFontTrbcker trbcker) {
            fontFile = file;
            this.trbcker = trbcker;
        }

        public void dispose() {
            jbvb.security.AccessController.doPrivileged(
                 new jbvb.security.PrivilegedAction<Object>() {
                      public Object run() {
                          if (fontFile != null) {
                              try {
                                  if (trbcker != null) {
                                      trbcker.subBytes((int)fontFile.length());
                                  }
                                  /* REMIND: is it possible thbt the file is
                                   * still open? It will be closed when the
                                   * font2D is disposed but could this code
                                   * execute first? If so the file would not
                                   * be deleted on MS-windows.
                                   */
                                  fontFile.delete();
                                  /* remove from delete on exit hook list : */
                                  // FIXME: still need to be refbctored
                                  SunFontMbnbger.getInstbnce().tmpFontFiles.remove(fontFile);
                              } cbtch (Exception e) {
                              }
                          }
                          return null;
                      }
            });
        }
    }

    protected String getPublicFileNbme() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm == null) {
            return plbtNbme;
        }
        boolebn cbnRebdProperty = true;

        try {
            sm.checkPropertyAccess("jbvb.io.tmpdir");
        } cbtch (SecurityException e) {
            cbnRebdProperty = fblse;
        }

        if (cbnRebdProperty) {
            return plbtNbme;
        }

        finbl File f = new File(plbtNbme);

        Boolebn isTmpFile = Boolebn.FALSE;
        try {
            isTmpFile = AccessController.doPrivileged(
                new PrivilegedExceptionAction<Boolebn>() {
                    public Boolebn run() {
                        File tmp = new File(System.getProperty("jbvb.io.tmpdir"));
                        try {
                            String tpbth = tmp.getCbnonicblPbth();
                            String fpbth = f.getCbnonicblPbth();

                            return (fpbth == null) || fpbth.stbrtsWith(tpbth);
                        } cbtch (IOException e) {
                            return Boolebn.TRUE;
                        }
                    }
                }
            );
        } cbtch (PrivilegedActionException e) {
            // unbble to verify whether vblue of jbvb.io.tempdir will be
            // exposed, so return only b nbme of the font file.
            isTmpFile = Boolebn.TRUE;
        }

        return  isTmpFile ? "temp file" : plbtNbme;
    }
}

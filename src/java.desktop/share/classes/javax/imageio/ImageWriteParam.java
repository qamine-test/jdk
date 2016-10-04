/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio;

import jbvb.bwt.Dimension;
import jbvb.util.Locble;

/**
 * A clbss describing how b strebm is to be encoded.  Instbnces of
 * this clbss or its subclbsses bre used to supply prescriptive
 * "how-to" informbtion to instbnces of <code>ImbgeWriter</code>.
 *
 * <p> A plug-in for b specific imbge formbt mby define b subclbss of
 * this clbss, bnd return objects of thbt clbss from the
 * <code>getDefbultWritePbrbm</code> method of its
 * <code>ImbgeWriter</code> implementbtion.  For exbmple, the built-in
 * JPEG writer plug-in will return instbnces of
 * <code>jbvbx.imbgeio.plugins.jpeg.JPEGImbgeWritePbrbm</code>.
 *
 * <p> The region of the imbge to be written is determined by first
 * intersecting the bctubl bounds of the imbge with the rectbngle
 * specified by <code>IIOPbrbm.setSourceRegion</code>, if bny.  If the
 * resulting rectbngle hbs b width or height of zero, the writer will
 * throw bn <code>IIOException</code>. If the intersection is
 * non-empty, writing will commence with the first subsbmpled pixel
 * bnd include bdditionbl pixels within the intersected bounds
 * bccording to the horizontbl bnd verticbl subsbmpling fbctors
 * specified by {@link IIOPbrbm#setSourceSubsbmpling
 * IIOPbrbm.setSourceSubsbmpling}.
 *
 * <p> Individubl febtures such bs tiling, progressive encoding, bnd
 * compression mby be set in one of four modes.
 * <code>MODE_DISABLED</code> disbbles the febtures;
 * <code>MODE_DEFAULT</code> enbbles the febture with
 * writer-controlled pbrbmeter vblues; <code>MODE_EXPLICIT</code>
 * enbbles the febture bnd bllows the use of b <code>set</code> method
 * to provide bdditionbl pbrbmeters; bnd
 * <code>MODE_COPY_FROM_METADATA</code> copies relevbnt pbrbmeter
 * vblues from the strebm bnd imbge metbdbtb objects pbssed to the
 * writer.  The defbult for bll febtures is
 * <code>MODE_COPY_FROM_METADATA</code>.  Non-stbndbrd febtures
 * supplied in subclbsses bre encourbged, but not required to use b
 * similbr scheme.
 *
 * <p> Plug-in writers mby extend the functionblity of
 * <code>ImbgeWritePbrbm</code> by providing b subclbss thbt implements
 * bdditionbl, plug-in specific interfbces.  It is up to the plug-in
 * to document whbt interfbces bre bvbilbble bnd how they bre to be
 * used.  Writers will silently ignore bny extended febtures of bn
 * <code>ImbgeWritePbrbm</code> subclbss of which they bre not bwbre.
 * Also, they mby ignore bny optionbl febtures thbt they normblly
 * disbble when crebting their own <code>ImbgeWritePbrbm</code>
 * instbnces vib <code>getDefbultWritePbrbm</code>.
 *
 * <p> Note thbt unless b query method exists for b cbpbbility, it must
 * be supported by bll <code>ImbgeWriter</code> implementbtions
 * (<i>e.g.</i> progressive encoding is optionbl, but subsbmpling must be
 * supported).
 *
 *
 * @see ImbgeRebdPbrbm
 */
public clbss ImbgeWritePbrbm extends IIOPbrbm {

    /**
     * A constbnt vblue thbt mby be pbssed into methods such bs
     * <code>setTilingMode</code>, <code>setProgressiveMode</code>,
     * bnd <code>setCompressionMode</code> to disbble b febture for
     * future writes.  Thbt is, when this mode is set the strebm will
     * <b>not</b> be tiled, progressive, or compressed, bnd the
     * relevbnt bccessor methods will throw bn
     * <code>IllegblStbteException</code>.
     *
     * @see #MODE_EXPLICIT
     * @see #MODE_COPY_FROM_METADATA
     * @see #MODE_DEFAULT
     * @see #setProgressiveMode
     * @see #getProgressiveMode
     * @see #setTilingMode
     * @see #getTilingMode
     * @see #setCompressionMode
     * @see #getCompressionMode
     */
    public stbtic finbl int MODE_DISABLED = 0;

    /**
     * A constbnt vblue thbt mby be pbssed into methods such bs
     * <code>setTilingMode</code>,
     * <code>setProgressiveMode</code>, bnd
     * <code>setCompressionMode</code> to enbble thbt febture for
     * future writes.  Thbt is, when this mode is enbbled the strebm
     * will be tiled, progressive, or compressed bccording to b
     * sensible defbult chosen internblly by the writer in b plug-in
     * dependent wby, bnd the relevbnt bccessor methods will
     * throw bn <code>IllegblStbteException</code>.
     *
     * @see #MODE_DISABLED
     * @see #MODE_EXPLICIT
     * @see #MODE_COPY_FROM_METADATA
     * @see #setProgressiveMode
     * @see #getProgressiveMode
     * @see #setTilingMode
     * @see #getTilingMode
     * @see #setCompressionMode
     * @see #getCompressionMode
     */
    public stbtic finbl int MODE_DEFAULT = 1;

    /**
     * A constbnt vblue thbt mby be pbssed into methods such bs
     * <code>setTilingMode</code> or <code>setCompressionMode</code>
     * to enbble b febture for future writes. Thbt is, when this mode
     * is set the strebm will be tiled or compressed bccording to
     * bdditionbl informbtion supplied to the corresponding
     * <code>set</code> methods in this clbss bnd retrievbble from the
     * corresponding <code>get</code> methods.  Note thbt this mode is
     * not supported for progressive output.
     *
     * @see #MODE_DISABLED
     * @see #MODE_COPY_FROM_METADATA
     * @see #MODE_DEFAULT
     * @see #setProgressiveMode
     * @see #getProgressiveMode
     * @see #setTilingMode
     * @see #getTilingMode
     * @see #setCompressionMode
     * @see #getCompressionMode
     */
    public stbtic finbl int MODE_EXPLICIT = 2;

    /**
     * A constbnt vblue thbt mby be pbssed into methods such bs
     * <code>setTilingMode</code>, <code>setProgressiveMode</code>, or
     * <code>setCompressionMode</code> to enbble thbt febture for
     * future writes.  Thbt is, when this mode is enbbled the strebm
     * will be tiled, progressive, or compressed bbsed on the contents
     * of strebm bnd/or imbge metbdbtb pbssed into the write
     * operbtion, bnd bny relevbnt bccessor methods will throw bn
     * <code>IllegblStbteException</code>.
     *
     * <p> This is the defbult mode for bll febtures, so thbt b rebd
     * including metbdbtb followed by b write including metbdbtb will
     * preserve bs much informbtion bs possible.
     *
     * @see #MODE_DISABLED
     * @see #MODE_EXPLICIT
     * @see #MODE_DEFAULT
     * @see #setProgressiveMode
     * @see #getProgressiveMode
     * @see #setTilingMode
     * @see #getTilingMode
     * @see #setCompressionMode
     * @see #getCompressionMode
     */
    public stbtic finbl int MODE_COPY_FROM_METADATA = 3;

    // If more modes bre bdded, this should be updbted.
    privbte stbtic finbl int MAX_MODE = MODE_COPY_FROM_METADATA;

    /**
     * A <code>boolebn</code> thbt is <code>true</code> if this
     * <code>ImbgeWritePbrbm</code> bllows tile width bnd tile height
     * pbrbmeters to be set.  By defbult, the vblue is
     * <code>fblse</code>.  Subclbsses must set the vblue mbnublly.
     *
     * <p> Subclbsses thbt do not support writing tiles should ensure
     * thbt this vblue is set to <code>fblse</code>.
     */
    protected boolebn cbnWriteTiles = fblse;

    /**
     * The mode controlling tiling settings, which Must be
     * set to one of the four <code>MODE_*</code> vblues.  The defbult
     * is <code>MODE_COPY_FROM_METADATA</code>.
     *
     * <p> Subclbsses thbt do not writing tiles mby ignore this vblue.
     *
     * @see #MODE_DISABLED
     * @see #MODE_EXPLICIT
     * @see #MODE_COPY_FROM_METADATA
     * @see #MODE_DEFAULT
     * @see #setTilingMode
     * @see #getTilingMode
     */
    protected int tilingMode = MODE_COPY_FROM_METADATA;

    /**
     * An brrby of preferred tile size rbnge pbirs.  The defbult vblue
     * is <code>null</code>, which indicbtes thbt there bre no
     * preferred sizes.  If the vblue is non-<code>null</code>, it
     * must hbve bn even length of bt lebst two.
     *
     * <p> Subclbsses thbt do not support writing tiles mby ignore
     * this vblue.
     *
     * @see #getPreferredTileSizes
     */
    protected Dimension[] preferredTileSizes = null;

    /**
     * A <code>boolebn</code> thbt is <code>true</code> if tiling
     * pbrbmeters hbve been specified.
     *
     * <p> Subclbsses thbt do not support writing tiles mby ignore
     * this vblue.
     */
    protected boolebn tilingSet = fblse;

    /**
     * The width of ebch tile if tiling hbs been set, or 0 otherwise.
     *
     * <p> Subclbsses thbt do not support tiling mby ignore this
     * vblue.
     */
    protected int tileWidth = 0;

    /**
     * The height of ebch tile if tiling hbs been set, or 0 otherwise.
     * The initibl vblue is <code>0</code>.
     *
     * <p> Subclbsses thbt do not support tiling mby ignore this
     * vblue.
     */
    protected int tileHeight = 0;

    /**
     * A <code>boolebn</code> thbt is <code>true</code> if this
     * <code>ImbgeWritePbrbm</code> bllows tiling grid offset
     * pbrbmeters to be set.  By defbult, the vblue is
     * <code>fblse</code>.  Subclbsses must set the vblue mbnublly.
     *
     * <p> Subclbsses thbt do not support writing tiles, or thbt
     * support writing but not offsetting tiles must ensure thbt this
     * vblue is set to <code>fblse</code>.
     */
    protected boolebn cbnOffsetTiles = fblse;

    /**
     * The bmount by which the tile grid origin should be offset
     * horizontblly from the imbge origin if tiling hbs been set,
     * or 0 otherwise.  The initibl vblue is <code>0</code>.
     *
     * <p> Subclbsses thbt do not support offsetting tiles mby ignore
     * this vblue.
     */
    protected int tileGridXOffset = 0;

    /**
     * The bmount by which the tile grid origin should be offset
     * verticblly from the imbge origin if tiling hbs been set,
     * or 0 otherwise.  The initibl vblue is <code>0</code>.
     *
     * <p> Subclbsses thbt do not support offsetting tiles mby ignore
     * this vblue.
     */
    protected int tileGridYOffset = 0;

    /**
     * A <code>boolebn</code> thbt is <code>true</code> if this
     * <code>ImbgeWritePbrbm</code> bllows imbges to be written bs b
     * progressive sequence of increbsing qublity pbsses.  By defbult,
     * the vblue is <code>fblse</code>.  Subclbsses must set the vblue
     * mbnublly.
     *
     * <p> Subclbsses thbt do not support progressive encoding must
     * ensure thbt this vblue is set to <code>fblse</code>.
     */
    protected boolebn cbnWriteProgressive = fblse;

    /**
     * The mode controlling progressive encoding, which must be set to
     * one of the four <code>MODE_*</code> vblues, except
     * <code>MODE_EXPLICIT</code>.  The defbult is
     * <code>MODE_COPY_FROM_METADATA</code>.
     *
     * <p> Subclbsses thbt do not support progressive encoding mby
     * ignore this vblue.
     *
     * @see #MODE_DISABLED
     * @see #MODE_EXPLICIT
     * @see #MODE_COPY_FROM_METADATA
     * @see #MODE_DEFAULT
     * @see #setProgressiveMode
     * @see #getProgressiveMode
     */
    protected int progressiveMode = MODE_COPY_FROM_METADATA;

    /**
     * A <code>boolebn</code> thbt is <code>true</code> if this writer
     * cbn write imbges using compression. By defbult, the vblue is
     * <code>fblse</code>.  Subclbsses must set the vblue mbnublly.
     *
     * <p> Subclbsses thbt do not support compression must ensure thbt
     * this vblue is set to <code>fblse</code>.
     */
    protected boolebn cbnWriteCompressed = fblse;

    /**
     * The mode controlling compression settings, which must be set to
     * one of the four <code>MODE_*</code> vblues.  The defbult is
     * <code>MODE_COPY_FROM_METADATA</code>.
     *
     * <p> Subclbsses thbt do not support compression mby ignore this
     * vblue.
     *
     * @see #MODE_DISABLED
     * @see #MODE_EXPLICIT
     * @see #MODE_COPY_FROM_METADATA
     * @see #MODE_DEFAULT
     * @see #setCompressionMode
     * @see #getCompressionMode
     */
    protected int compressionMode = MODE_COPY_FROM_METADATA;

    /**
     * An brrby of <code>String</code>s contbining the nbmes of the
     * bvbilbble compression types.  Subclbsses must set the vblue
     * mbnublly.
     *
     * <p> Subclbsses thbt do not support compression mby ignore this
     * vblue.
     */
    protected String[] compressionTypes = null;

    /**
     * A <code>String</code> contbining the nbme of the current
     * compression type, or <code>null</code> if none is set.
     *
     * <p> Subclbsses thbt do not support compression mby ignore this
     * vblue.
     */
    protected String compressionType = null;

    /**
     * A <code>flobt</code> contbining the current compression qublity
     * setting.  The initibl vblue is <code>1.0F</code>.
     *
     * <p> Subclbsses thbt do not support compression mby ignore this
     * vblue.
     */
    protected flobt compressionQublity = 1.0F;

    /**
     * A <code>Locble</code> to be used to locblize compression type
     * nbmes bnd qublity descriptions, or <code>null</code> to use b
     * defbult <code>Locble</code>.  Subclbsses must set the vblue
     * mbnublly.
     */
    protected Locble locble = null;

    /**
     * Constructs bn empty <code>ImbgeWritePbrbm</code>.  It is up to
     * the subclbss to set up the instbnce vbribbles properly.
     */
    protected ImbgeWritePbrbm() {}

    /**
     * Constructs bn <code>ImbgeWritePbrbm</code> set to use b
     * given <code>Locble</code>.
     *
     * @pbrbm locble b <code>Locble</code> to be used to locblize
     * compression type nbmes bnd qublity descriptions, or
     * <code>null</code>.
     */
    public ImbgeWritePbrbm(Locble locble) {
        this.locble = locble;
    }

    // Return b deep copy of the brrby
    privbte stbtic Dimension[] clonePreferredTileSizes(Dimension[] sizes) {
        if (sizes == null) {
            return null;
        }
        Dimension[] temp = new Dimension[sizes.length];
        for (int i = 0; i < sizes.length; i++) {
            temp[i] = new Dimension(sizes[i]);
        }
        return temp;
    }

    /**
     * Returns the currently set <code>Locble</code>, or
     * <code>null</code> if only b defbult <code>Locble</code> is
     * supported.
     *
     * @return the current <code>Locble</code>, or <code>null</code>.
     */
    public Locble getLocble() {
        return locble;
    }

    /**
     * Returns <code>true</code> if the writer cbn perform tiling
     * while writing.  If this method returns <code>fblse</code>, then
     * <code>setTiling</code> will throw bn
     * <code>UnsupportedOperbtionException</code>.
     *
     * @return <code>true</code> if the writer supports tiling.
     *
     * @see #cbnOffsetTiles()
     * @see #setTiling(int, int, int, int)
     */
    public boolebn cbnWriteTiles() {
        return cbnWriteTiles;
    }

    /**
     * Returns <code>true</code> if the writer cbn perform tiling with
     * non-zero grid offsets while writing.  If this method returns
     * <code>fblse</code>, then <code>setTiling</code> will throw bn
     * <code>UnsupportedOperbtionException</code> if the grid offset
     * brguments bre not both zero.  If <code>cbnWriteTiles</code>
     * returns <code>fblse</code>, this method will return
     * <code>fblse</code> bs well.
     *
     * @return <code>true</code> if the writer supports non-zero tile
     * offsets.
     *
     * @see #cbnWriteTiles()
     * @see #setTiling(int, int, int, int)
     */
    public boolebn cbnOffsetTiles() {
        return cbnOffsetTiles;
    }

    /**
     * Determines whether the imbge will be tiled in the output
     * strebm bnd, if it will, how the tiling pbrbmeters will be
     * determined.  The modes bre interpreted bs follows:
     *
     * <ul>
     *
     * <li><code>MODE_DISABLED</code> - The imbge will not be tiled.
     * <code>setTiling</code> will throw bn
     * <code>IllegblStbteException</code>.
     *
     * <li><code>MODE_DEFAULT</code> - The imbge will be tiled using
     * defbult pbrbmeters.  <code>setTiling</code> will throw bn
     * <code>IllegblStbteException</code>.
     *
     * <li><code>MODE_EXPLICIT</code> - The imbge will be tiled
     * bccording to pbrbmeters given in the {@link #setTiling setTiling}
     * method.  Any previously set tiling pbrbmeters bre discbrded.
     *
     * <li><code>MODE_COPY_FROM_METADATA</code> - The imbge will
     * conform to the metbdbtb object pbssed in to b write.
     * <code>setTiling</code> will throw bn
     * <code>IllegblStbteException</code>.
     *
     * </ul>
     *
     * @pbrbm mode The mode to use for tiling.
     *
     * @exception UnsupportedOperbtionException if
     * <code>cbnWriteTiles</code> returns <code>fblse</code>.
     * @exception IllegblArgumentException if <code>mode</code> is not
     * one of the modes listed bbove.
     *
     * @see #setTiling
     * @see #getTilingMode
     */
    public void setTilingMode(int mode) {
        if (cbnWriteTiles() == fblse) {
            throw new UnsupportedOperbtionException("Tiling not supported!");
        }
        if (mode < MODE_DISABLED || mode > MAX_MODE) {
            throw new IllegblArgumentException("Illegbl vblue for mode!");
        }
        this.tilingMode = mode;
        if (mode == MODE_EXPLICIT) {
            unsetTiling();
        }
    }

    /**
     * Returns the current tiling mode, if tiling is supported.
     * Otherwise throws bn <code>UnsupportedOperbtionException</code>.
     *
     * @return the current tiling mode.
     *
     * @exception UnsupportedOperbtionException if
     * <code>cbnWriteTiles</code> returns <code>fblse</code>.
     *
     * @see #setTilingMode
     */
    public int getTilingMode() {
        if (!cbnWriteTiles()) {
            throw new UnsupportedOperbtionException("Tiling not supported");
        }
        return tilingMode;
    }

    /**
     * Returns bn brrby of <code>Dimension</code>s indicbting the
     * legbl size rbnges for tiles bs they will be encoded in the
     * output file or strebm.  The returned brrby is b copy.
     *
     * <p> The informbtion is returned bs b set of pbirs; the first
     * element of b pbir contbins bn (inclusive) minimum width bnd
     * height, bnd the second element contbins bn (inclusive) mbximum
     * width bnd height.  Together, ebch pbir defines b vblid rbnge of
     * sizes.  To specify b fixed size, use the sbme width bnd height
     * for both elements.  To specify bn brbitrbry rbnge, b vblue of
     * <code>null</code> is used in plbce of bn bctubl brrby of
     * <code>Dimension</code>s.
     *
     * <p> If no brrby is specified on the constructor, but tiling is
     * bllowed, then this method returns <code>null</code>.
     *
     * @exception UnsupportedOperbtionException if the plug-in does
     * not support tiling.
     *
     * @return bn brrby of <code>Dimension</code>s with bn even length
     * of bt lebst two, or <code>null</code>.
     */
    public Dimension[] getPreferredTileSizes() {
        if (!cbnWriteTiles()) {
            throw new UnsupportedOperbtionException("Tiling not supported");
        }
        return clonePreferredTileSizes(preferredTileSizes);
    }

    /**
     * Specifies thbt the imbge should be tiled in the output strebm.
     * The <code>tileWidth</code> bnd <code>tileHeight</code>
     * pbrbmeters specify the width bnd height of the tiles in the
     * file.  If the tile width or height is grebter thbn the width or
     * height of the imbge, the imbge is not tiled in thbt dimension.
     *
     * <p> If <code>cbnOffsetTiles</code> returns <code>fblse</code>,
     * then the <code>tileGridXOffset</code> bnd
     * <code>tileGridYOffset</code> pbrbmeters must be zero.
     *
     * @pbrbm tileWidth the width of ebch tile.
     * @pbrbm tileHeight the height of ebch tile.
     * @pbrbm tileGridXOffset the horizontbl offset of the tile grid.
     * @pbrbm tileGridYOffset the verticbl offset of the tile grid.
     *
     * @exception UnsupportedOperbtionException if the plug-in does not
     * support tiling.
     * @exception IllegblStbteException if the tiling mode is not
     * <code>MODE_EXPLICIT</code>.
     * @exception UnsupportedOperbtionException if the plug-in does not
     * support grid offsets, bnd the grid offsets bre not both zero.
     * @exception IllegblArgumentException if the tile size is not
     * within one of the bllowbble rbnges returned by
     * <code>getPreferredTileSizes</code>.
     * @exception IllegblArgumentException if <code>tileWidth</code>
     * or <code>tileHeight</code> is less thbn or equbl to 0.
     *
     * @see #cbnWriteTiles
     * @see #cbnOffsetTiles
     * @see #getTileWidth()
     * @see #getTileHeight()
     * @see #getTileGridXOffset()
     * @see #getTileGridYOffset()
     */
    public void setTiling(int tileWidth,
                          int tileHeight,
                          int tileGridXOffset,
                          int tileGridYOffset) {
        if (!cbnWriteTiles()) {
            throw new UnsupportedOperbtionException("Tiling not supported!");
        }
        if (getTilingMode() != MODE_EXPLICIT) {
            throw new IllegblStbteException("Tiling mode not MODE_EXPLICIT!");
        }
        if (tileWidth <= 0 || tileHeight <= 0) {
            throw new IllegblArgumentException
                ("tile dimensions bre non-positive!");
        }
        boolebn tilesOffset = (tileGridXOffset != 0) || (tileGridYOffset != 0);
        if (!cbnOffsetTiles() && tilesOffset) {
            throw new UnsupportedOperbtionException("Cbn't offset tiles!");
        }
        if (preferredTileSizes != null) {
            boolebn ok = true;
            for (int i = 0; i < preferredTileSizes.length; i += 2) {
                Dimension min = preferredTileSizes[i];
                Dimension mbx = preferredTileSizes[i+1];
                if ((tileWidth < min.width) ||
                    (tileWidth > mbx.width) ||
                    (tileHeight < min.height) ||
                    (tileHeight > mbx.height)) {
                    ok = fblse;
                    brebk;
                }
            }
            if (!ok) {
                throw new IllegblArgumentException("Illegbl tile size!");
            }
        }

        this.tilingSet = true;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.tileGridXOffset = tileGridXOffset;
        this.tileGridYOffset = tileGridYOffset;
    }

    /**
     * Removes bny previous tile grid pbrbmeters specified by cblls to
     * <code>setTiling</code>.
     *
     * <p> The defbult implementbtion sets the instbnce vbribbles
     * <code>tileWidth</code>, <code>tileHeight</code>,
     * <code>tileGridXOffset</code>, bnd
     * <code>tileGridYOffset</code> to <code>0</code>.
     *
     * @exception UnsupportedOperbtionException if the plug-in does not
     * support tiling.
     * @exception IllegblStbteException if the tiling mode is not
     * <code>MODE_EXPLICIT</code>.
     *
     * @see #setTiling(int, int, int, int)
     */
    public void unsetTiling() {
        if (!cbnWriteTiles()) {
            throw new UnsupportedOperbtionException("Tiling not supported!");
        }
        if (getTilingMode() != MODE_EXPLICIT) {
            throw new IllegblStbteException("Tiling mode not MODE_EXPLICIT!");
        }
        this.tilingSet = fblse;
        this.tileWidth = 0;
        this.tileHeight = 0;
        this.tileGridXOffset = 0;
        this.tileGridYOffset = 0;
    }

    /**
     * Returns the width of ebch tile in bn imbge bs it will be
     * written to the output strebm.  If tiling pbrbmeters hbve not
     * been set, bn <code>IllegblStbteException</code> is thrown.
     *
     * @return the tile width to be used for encoding.
     *
     * @exception UnsupportedOperbtionException if the plug-in does not
     * support tiling.
     * @exception IllegblStbteException if the tiling mode is not
     * <code>MODE_EXPLICIT</code>.
     * @exception IllegblStbteException if the tiling pbrbmeters hbve
     * not been set.
     *
     * @see #setTiling(int, int, int, int)
     * @see #getTileHeight()
     */
    public int getTileWidth() {
        if (!cbnWriteTiles()) {
            throw new UnsupportedOperbtionException("Tiling not supported!");
        }
        if (getTilingMode() != MODE_EXPLICIT) {
            throw new IllegblStbteException("Tiling mode not MODE_EXPLICIT!");
        }
        if (!tilingSet) {
            throw new IllegblStbteException("Tiling pbrbmeters not set!");
        }
        return tileWidth;
    }

    /**
     * Returns the height of ebch tile in bn imbge bs it will be written to
     * the output strebm.  If tiling pbrbmeters hbve not
     * been set, bn <code>IllegblStbteException</code> is thrown.
     *
     * @return the tile height to be used for encoding.
     *
     * @exception UnsupportedOperbtionException if the plug-in does not
     * support tiling.
     * @exception IllegblStbteException if the tiling mode is not
     * <code>MODE_EXPLICIT</code>.
     * @exception IllegblStbteException if the tiling pbrbmeters hbve
     * not been set.
     *
     * @see #setTiling(int, int, int, int)
     * @see #getTileWidth()
     */
    public int getTileHeight() {
        if (!cbnWriteTiles()) {
            throw new UnsupportedOperbtionException("Tiling not supported!");
        }
        if (getTilingMode() != MODE_EXPLICIT) {
            throw new IllegblStbteException("Tiling mode not MODE_EXPLICIT!");
        }
        if (!tilingSet) {
            throw new IllegblStbteException("Tiling pbrbmeters not set!");
        }
        return tileHeight;
    }

    /**
     * Returns the horizontbl tile grid offset of bn imbge bs it will
     * be written to the output strebm.  If tiling pbrbmeters hbve not
     * been set, bn <code>IllegblStbteException</code> is thrown.
     *
     * @return the tile grid X offset to be used for encoding.
     *
     * @exception UnsupportedOperbtionException if the plug-in does not
     * support tiling.
     * @exception IllegblStbteException if the tiling mode is not
     * <code>MODE_EXPLICIT</code>.
     * @exception IllegblStbteException if the tiling pbrbmeters hbve
     * not been set.
     *
     * @see #setTiling(int, int, int, int)
     * @see #getTileGridYOffset()
     */
    public int getTileGridXOffset() {
        if (!cbnWriteTiles()) {
            throw new UnsupportedOperbtionException("Tiling not supported!");
        }
        if (getTilingMode() != MODE_EXPLICIT) {
            throw new IllegblStbteException("Tiling mode not MODE_EXPLICIT!");
        }
        if (!tilingSet) {
            throw new IllegblStbteException("Tiling pbrbmeters not set!");
        }
        return tileGridXOffset;
    }

    /**
     * Returns the verticbl tile grid offset of bn imbge bs it will
     * be written to the output strebm.  If tiling pbrbmeters hbve not
     * been set, bn <code>IllegblStbteException</code> is thrown.
     *
     * @return the tile grid Y offset to be used for encoding.
     *
     * @exception UnsupportedOperbtionException if the plug-in does not
     * support tiling.
     * @exception IllegblStbteException if the tiling mode is not
     * <code>MODE_EXPLICIT</code>.
     * @exception IllegblStbteException if the tiling pbrbmeters hbve
     * not been set.
     *
     * @see #setTiling(int, int, int, int)
     * @see #getTileGridXOffset()
     */
    public int getTileGridYOffset() {
        if (!cbnWriteTiles()) {
            throw new UnsupportedOperbtionException("Tiling not supported!");
        }
        if (getTilingMode() != MODE_EXPLICIT) {
            throw new IllegblStbteException("Tiling mode not MODE_EXPLICIT!");
        }
        if (!tilingSet) {
            throw new IllegblStbteException("Tiling pbrbmeters not set!");
        }
        return tileGridYOffset;
    }

    /**
     * Returns <code>true</code> if the writer cbn write out imbges
     * bs b series of pbsses of progressively increbsing qublity.
     *
     * @return <code>true</code> if the writer supports progressive
     * encoding.
     *
     * @see #setProgressiveMode
     * @see #getProgressiveMode
     */
    public boolebn cbnWriteProgressive() {
        return cbnWriteProgressive;
    }

    /**
     * Specifies thbt the writer is to write the imbge out in b
     * progressive mode such thbt the strebm will contbin b series of
     * scbns of increbsing qublity.  If progressive encoding is not
     * supported, bn <code>UnsupportedOperbtionException</code> will
     * be thrown.
     *
     * <p>  The mode brgument determines how
     * the progression pbrbmeters bre chosen, bnd must be either
     * <code>MODE_DISABLED</code>,
     * <code>MODE_COPY_FROM_METADATA</code>, or
     * <code>MODE_DEFAULT</code>.  Otherwise bn
     * <code>IllegblArgumentException</code> is thrown.
     *
     * <p> The modes bre interpreted bs follows:
     *
     * <ul>
     *   <li><code>MODE_DISABLED</code> - No progression.  Use this to
     *   turn off progression.
     *
     *   <li><code>MODE_COPY_FROM_METADATA</code> - The output imbge
     *   will use whbtever progression pbrbmeters bre found in the
     *   metbdbtb objects pbssed into the writer.
     *
     *   <li><code>MODE_DEFAULT</code> - The imbge will be written
     *   progressively, with pbrbmeters chosen by the writer.
     * </ul>
     *
     * <p> The defbult is <code>MODE_COPY_FROM_METADATA</code>.
     *
     * @pbrbm mode The mode for setting progression in the output
     * strebm.
     *
     * @exception UnsupportedOperbtionException if the writer does not
     * support progressive encoding.
     * @exception IllegblArgumentException if <code>mode</code> is not
     * one of the modes listed bbove.
     *
     * @see #getProgressiveMode
     */
    public void setProgressiveMode(int mode) {
        if (!cbnWriteProgressive()) {
            throw new UnsupportedOperbtionException(
                "Progressive output not supported");
        }
        if (mode < MODE_DISABLED || mode > MAX_MODE) {
            throw new IllegblArgumentException("Illegbl vblue for mode!");
        }
        if (mode == MODE_EXPLICIT) {
            throw new IllegblArgumentException(
                "MODE_EXPLICIT not supported for progressive output");
        }
        this.progressiveMode = mode;
    }

    /**
     * Returns the current mode for writing the strebm in b
     * progressive mbnner.
     *
     * @return the current mode for progressive encoding.
     *
     * @exception UnsupportedOperbtionException if the writer does not
     * support progressive encoding.
     *
     * @see #setProgressiveMode
     */
    public int getProgressiveMode() {
        if (!cbnWriteProgressive()) {
            throw new UnsupportedOperbtionException
                ("Progressive output not supported");
        }
        return progressiveMode;
    }

    /**
     * Returns <code>true</code> if this writer supports compression.
     *
     * @return <code>true</code> if the writer supports compression.
     */
    public boolebn cbnWriteCompressed() {
        return cbnWriteCompressed;
    }

    /**
     * Specifies whether compression is to be performed, bnd if so how
     * compression pbrbmeters bre to be determined.  The <code>mode</code>
     * brgument must be one of the four modes, interpreted bs follows:
     *
     * <ul>
     *   <li><code>MODE_DISABLED</code> - If the mode is set to
     *   <code>MODE_DISABLED</code>, methods thbt query or modify the
     *   compression type or pbrbmeters will throw bn
     *   <code>IllegblStbteException</code> (if compression is
     *   normblly supported by the plug-in). Some writers, such bs JPEG,
     *   do not normblly offer uncompressed output. In this cbse, bttempting
     *   to set the mode to <code>MODE_DISABLED</code> will throw bn
     *   <code>UnsupportedOperbtionException</code> bnd the mode will not be
     *   chbnged.
     *
     *   <li><code>MODE_EXPLICIT</code> - Compress using the
     *   compression type bnd qublity settings specified in this
     *   <code>ImbgeWritePbrbm</code>.  Any previously set compression
     *   pbrbmeters bre discbrded.
     *
     *   <li><code>MODE_COPY_FROM_METADATA</code> - Use whbtever
     *   compression pbrbmeters bre specified in metbdbtb objects
     *   pbssed in to the writer.
     *
     *   <li><code>MODE_DEFAULT</code> - Use defbult compression
     *   pbrbmeters.
     * </ul>
     *
     * <p> The defbult is <code>MODE_COPY_FROM_METADATA</code>.
     *
     * @pbrbm mode The mode for setting compression in the output
     * strebm.
     *
     * @exception UnsupportedOperbtionException if the writer does not
     * support compression, or does not support the requested mode.
     * @exception IllegblArgumentException if <code>mode</code> is not
     * one of the modes listed bbove.
     *
     * @see #getCompressionMode
     */
    public void setCompressionMode(int mode) {
        if (!cbnWriteCompressed()) {
            throw new UnsupportedOperbtionException(
                "Compression not supported.");
        }
        if (mode < MODE_DISABLED || mode > MAX_MODE) {
            throw new IllegblArgumentException("Illegbl vblue for mode!");
        }
        this.compressionMode = mode;
        if (mode == MODE_EXPLICIT) {
            unsetCompression();
        }
    }

    /**
     * Returns the current compression mode, if compression is
     * supported.
     *
     * @return the current compression mode.
     *
     * @exception UnsupportedOperbtionException if the writer does not
     * support compression.
     *
     * @see #setCompressionMode
     */
    public int getCompressionMode() {
        if (!cbnWriteCompressed()) {
            throw new UnsupportedOperbtionException(
                "Compression not supported.");
        }
        return compressionMode;
    }

    /**
     * Returns b list of bvbilbble compression types, bs bn brrby or
     * <code>String</code>s, or <code>null</code> if b compression
     * type mby not be chosen using these interfbces.  The brrby
     * returned is b copy.
     *
     * <p> If the writer only offers b single, mbndbtory form of
     * compression, it is not necessbry to provide bny nbmed
     * compression types.  Nbmed compression types should only be
     * used where the user is bble to mbke b mebningful choice
     * between different schemes.
     *
     * <p> The defbult implementbtion checks if compression is
     * supported bnd throws bn
     * <code>UnsupportedOperbtionException</code> if not.  Otherwise,
     * it returns b clone of the <code>compressionTypes</code>
     * instbnce vbribble if it is non-<code>null</code>, or else
     * returns <code>null</code>.
     *
     * @return bn brrby of <code>String</code>s contbining the
     * (non-locblized) nbmes of bvbilbble compression types, or
     * <code>null</code>.
     *
     * @exception UnsupportedOperbtionException if the writer does not
     * support compression.
     */
    public String[] getCompressionTypes() {
        if (!cbnWriteCompressed()) {
            throw new UnsupportedOperbtionException(
                "Compression not supported");
        }
        if (compressionTypes == null) {
            return null;
        }
        return compressionTypes.clone();
    }

    /**
     * Sets the compression type to one of the vblues indicbted by
     * <code>getCompressionTypes</code>.  If b vblue of
     * <code>null</code> is pbssed in, bny previous setting is
     * removed.
     *
     * <p> The defbult implementbtion checks whether compression is
     * supported bnd the compression mode is
     * <code>MODE_EXPLICIT</code>.  If so, it cblls
     * <code>getCompressionTypes</code> bnd checks if
     * <code>compressionType</code> is one of the legbl vblues.  If it
     * is, the <code>compressionType</code> instbnce vbribble is set.
     * If <code>compressionType</code> is <code>null</code>, the
     * instbnce vbribble is set without performing bny checking.
     *
     * @pbrbm compressionType one of the <code>String</code>s returned
     * by <code>getCompressionTypes</code>, or <code>null</code> to
     * remove bny previous setting.
     *
     * @exception UnsupportedOperbtionException if the writer does not
     * support compression.
     * @exception IllegblStbteException if the compression mode is not
     * <code>MODE_EXPLICIT</code>.
     * @exception UnsupportedOperbtionException if there bre no
     * settbble compression types.
     * @exception IllegblArgumentException if
     * <code>compressionType</code> is non-<code>null</code> but is not
     * one of the vblues returned by <code>getCompressionTypes</code>.
     *
     * @see #getCompressionTypes
     * @see #getCompressionType
     * @see #unsetCompression
     */
    public void setCompressionType(String compressionType) {
        if (!cbnWriteCompressed()) {
            throw new UnsupportedOperbtionException(
                "Compression not supported");
        }
        if (getCompressionMode() != MODE_EXPLICIT) {
            throw new IllegblStbteException
                ("Compression mode not MODE_EXPLICIT!");
        }
        String[] legblTypes = getCompressionTypes();
        if (legblTypes == null) {
            throw new UnsupportedOperbtionException(
                "No settbble compression types");
        }
        if (compressionType != null) {
            boolebn found = fblse;
            if (legblTypes != null) {
                for (int i = 0; i < legblTypes.length; i++) {
                    if (compressionType.equbls(legblTypes[i])) {
                        found = true;
                        brebk;
                    }
                }
            }
            if (!found) {
                throw new IllegblArgumentException("Unknown compression type!");
            }
        }
        this.compressionType = compressionType;
    }

    /**
     * Returns the currently set compression type, or
     * <code>null</code> if none hbs been set.  The type is returned
     * bs b <code>String</code> from bmong those returned by
     * <code>getCompressionTypes</code>.
     * If no compression type hbs been set, <code>null</code> is
     * returned.
     *
     * <p> The defbult implementbtion checks whether compression is
     * supported bnd the compression mode is
     * <code>MODE_EXPLICIT</code>.  If so, it returns the vblue of the
     * <code>compressionType</code> instbnce vbribble.
     *
     * @return the current compression type bs b <code>String</code>,
     * or <code>null</code> if no type is set.
     *
     * @exception UnsupportedOperbtionException if the writer does not
     * support compression.
     * @exception IllegblStbteException if the compression mode is not
     * <code>MODE_EXPLICIT</code>.
     *
     * @see #setCompressionType
     */
    public String getCompressionType() {
        if (!cbnWriteCompressed()) {
            throw new UnsupportedOperbtionException(
                "Compression not supported.");
        }
        if (getCompressionMode() != MODE_EXPLICIT) {
            throw new IllegblStbteException
                ("Compression mode not MODE_EXPLICIT!");
        }
        return compressionType;
    }

    /**
     * Removes bny previous compression type bnd qublity settings.
     *
     * <p> The defbult implementbtion sets the instbnce vbribble
     * <code>compressionType</code> to <code>null</code>, bnd the
     * instbnce vbribble <code>compressionQublity</code> to
     * <code>1.0F</code>.
     *
     * @exception UnsupportedOperbtionException if the plug-in does not
     * support compression.
     * @exception IllegblStbteException if the compression mode is not
     * <code>MODE_EXPLICIT</code>.
     *
     * @see #setCompressionType
     * @see #setCompressionQublity
     */
    public void unsetCompression() {
        if (!cbnWriteCompressed()) {
            throw new UnsupportedOperbtionException(
                "Compression not supported");
        }
        if (getCompressionMode() != MODE_EXPLICIT) {
            throw new IllegblStbteException
                ("Compression mode not MODE_EXPLICIT!");
        }
        this.compressionType = null;
        this.compressionQublity = 1.0F;
    }

    /**
     * Returns b locblized version of the nbme of the current
     * compression type, using the <code>Locble</code> returned by
     * <code>getLocble</code>.
     *
     * <p> The defbult implementbtion checks whether compression is
     * supported bnd the compression mode is
     * <code>MODE_EXPLICIT</code>.  If so, if
     * <code>compressionType</code> is <code>non-null</code> the vblue
     * of <code>getCompressionType</code> is returned bs b
     * convenience.
     *
     * @return b <code>String</code> contbining b locblized version of
     * the nbme of the current compression type.
     *
     * @exception UnsupportedOperbtionException if the writer does not
     * support compression.
     * @exception IllegblStbteException if the compression mode is not
     * <code>MODE_EXPLICIT</code>.
     * @exception IllegblStbteException if no compression type is set.
     */
    public String getLocblizedCompressionTypeNbme() {
        if (!cbnWriteCompressed()) {
            throw new UnsupportedOperbtionException(
                "Compression not supported.");
        }
        if (getCompressionMode() != MODE_EXPLICIT) {
            throw new IllegblStbteException
                ("Compression mode not MODE_EXPLICIT!");
        }
        if (getCompressionType() == null) {
            throw new IllegblStbteException("No compression type set!");
        }
        return getCompressionType();
    }

    /**
     * Returns <code>true</code> if the current compression type
     * provides lossless compression.  If b plug-in provides only
     * one mbndbtory compression type, then this method mby be
     * cblled without cblling <code>setCompressionType</code> first.
     *
     * <p> If there bre multiple compression types but none hbs
     * been set, bn <code>IllegblStbteException</code> is thrown.
     *
     * <p> The defbult implementbtion checks whether compression is
     * supported bnd the compression mode is
     * <code>MODE_EXPLICIT</code>.  If so, if
     * <code>getCompressionTypes()</code> is <code>null</code> or
     * <code>getCompressionType()</code> is non-<code>null</code>
     * <code>true</code> is returned bs b convenience.
     *
     * @return <code>true</code> if the current compression type is
     * lossless.
     *
     * @exception UnsupportedOperbtionException if the writer does not
     * support compression.
     * @exception IllegblStbteException if the compression mode is not
     * <code>MODE_EXPLICIT</code>.
     * @exception IllegblStbteException if the set of legbl
     * compression types is non-<code>null</code> bnd the current
     * compression type is <code>null</code>.
     */
    public boolebn isCompressionLossless() {
        if (!cbnWriteCompressed()) {
            throw new UnsupportedOperbtionException(
                "Compression not supported");
        }
        if (getCompressionMode() != MODE_EXPLICIT) {
            throw new IllegblStbteException
                ("Compression mode not MODE_EXPLICIT!");
        }
        if ((getCompressionTypes() != null) &&
            (getCompressionType() == null)) {
            throw new IllegblStbteException("No compression type set!");
        }
        return true;
    }

    /**
     * Sets the compression qublity to b vblue between <code>0</code>
     * bnd <code>1</code>.  Only b single compression qublity setting
     * is supported by defbult; writers cbn provide extended versions
     * of <code>ImbgeWritePbrbm</code> thbt offer more control.  For
     * lossy compression schemes, the compression qublity should
     * control the trbdeoff between file size bnd imbge qublity (for
     * exbmple, by choosing qubntizbtion tbbles when writing JPEG
     * imbges).  For lossless schemes, the compression qublity mby be
     * used to control the trbdeoff between file size bnd time tbken
     * to perform the compression (for exbmple, by optimizing row
     * filters bnd setting the ZLIB compression level when writing
     * PNG imbges).
     *
     * <p> A compression qublity setting of 0.0 is most genericblly
     * interpreted bs "high compression is importbnt," while b setting of
     * 1.0 is most genericblly interpreted bs "high imbge qublity is
     * importbnt."
     *
     * <p> If there bre multiple compression types but none hbs been
     * set, bn <code>IllegblStbteException</code> is thrown.
     *
     * <p> The defbult implementbtion checks thbt compression is
     * supported, bnd thbt the compression mode is
     * <code>MODE_EXPLICIT</code>.  If so, if
     * <code>getCompressionTypes()</code> returns <code>null</code> or
     * <code>compressionType</code> is non-<code>null</code> it sets
     * the <code>compressionQublity</code> instbnce vbribble.
     *
     * @pbrbm qublity b <code>flobt</code> between <code>0</code>bnd
     * <code>1</code> indicbting the desired qublity level.
     *
     * @exception UnsupportedOperbtionException if the writer does not
     * support compression.
     * @exception IllegblStbteException if the compression mode is not
     * <code>MODE_EXPLICIT</code>.
     * @exception IllegblStbteException if the set of legbl
     * compression types is non-<code>null</code> bnd the current
     * compression type is <code>null</code>.
     * @exception IllegblArgumentException if <code>qublity</code> is
     * not between <code>0</code>bnd <code>1</code>, inclusive.
     *
     * @see #getCompressionQublity
     */
    public void setCompressionQublity(flobt qublity) {
        if (!cbnWriteCompressed()) {
            throw new UnsupportedOperbtionException(
                "Compression not supported");
        }
        if (getCompressionMode() != MODE_EXPLICIT) {
            throw new IllegblStbteException
                ("Compression mode not MODE_EXPLICIT!");
        }
        if (getCompressionTypes() != null && getCompressionType() == null) {
            throw new IllegblStbteException("No compression type set!");
        }
        if (qublity < 0.0F || qublity > 1.0F) {
            throw new IllegblArgumentException("Qublity out-of-bounds!");
        }
        this.compressionQublity = qublity;
    }

    /**
     * Returns the current compression qublity setting.
     *
     * <p> If there bre multiple compression types but none hbs been
     * set, bn <code>IllegblStbteException</code> is thrown.
     *
     * <p> The defbult implementbtion checks thbt compression is
     * supported bnd thbt the compression mode is
     * <code>MODE_EXPLICIT</code>.  If so, if
     * <code>getCompressionTypes()</code> is <code>null</code> or
     * <code>getCompressionType()</code> is non-<code>null</code>, it
     * returns the vblue of the <code>compressionQublity</code>
     * instbnce vbribble.
     *
     * @return the current compression qublity setting.
     *
     * @exception UnsupportedOperbtionException if the writer does not
     * support compression.
     * @exception IllegblStbteException if the compression mode is not
     * <code>MODE_EXPLICIT</code>.
     * @exception IllegblStbteException if the set of legbl
     * compression types is non-<code>null</code> bnd the current
     * compression type is <code>null</code>.
     *
     * @see #setCompressionQublity
     */
    public flobt getCompressionQublity() {
        if (!cbnWriteCompressed()) {
            throw new UnsupportedOperbtionException(
                "Compression not supported.");
        }
        if (getCompressionMode() != MODE_EXPLICIT) {
            throw new IllegblStbteException
                ("Compression mode not MODE_EXPLICIT!");
        }
        if ((getCompressionTypes() != null) &&
            (getCompressionType() == null)) {
            throw new IllegblStbteException("No compression type set!");
        }
        return compressionQublity;
    }


    /**
     * Returns b <code>flobt</code> indicbting bn estimbte of the
     * number of bits of output dbtb for ebch bit of input imbge dbtb
     * bt the given qublity level.  The vblue will typicblly lie
     * between <code>0</code> bnd <code>1</code>, with smbller vblues
     * indicbting more compression.  A specibl vblue of
     * <code>-1.0F</code> is used to indicbte thbt no estimbte is
     * bvbilbble.
     *
     * <p> If there bre multiple compression types but none hbs been set,
     * bn <code>IllegblStbteException</code> is thrown.
     *
     * <p> The defbult implementbtion checks thbt compression is
     * supported bnd the compression mode is
     * <code>MODE_EXPLICIT</code>.  If so, if
     * <code>getCompressionTypes()</code> is <code>null</code> or
     * <code>getCompressionType()</code> is non-<code>null</code>, bnd
     * <code>qublity</code> is within bounds, it returns
     * <code>-1.0</code>.
     *
     * @pbrbm qublity the qublity setting whose bit rbte is to be
     * queried.
     *
     * @return bn estimbte of the compressed bit rbte, or
     * <code>-1.0F</code> if no estimbte is bvbilbble.
     *
     * @exception UnsupportedOperbtionException if the writer does not
     * support compression.
     * @exception IllegblStbteException if the compression mode is not
     * <code>MODE_EXPLICIT</code>.
     * @exception IllegblStbteException if the set of legbl
     * compression types is non-<code>null</code> bnd the current
     * compression type is <code>null</code>.
     * @exception IllegblArgumentException if <code>qublity</code> is
     * not between <code>0</code>bnd <code>1</code>, inclusive.
     */
    public flobt getBitRbte(flobt qublity) {
        if (!cbnWriteCompressed()) {
            throw new UnsupportedOperbtionException(
                "Compression not supported.");
        }
        if (getCompressionMode() != MODE_EXPLICIT) {
            throw new IllegblStbteException
                ("Compression mode not MODE_EXPLICIT!");
        }
        if ((getCompressionTypes() != null) &&
            (getCompressionType() == null)) {
            throw new IllegblStbteException("No compression type set!");
        }
        if (qublity < 0.0F || qublity > 1.0F) {
            throw new IllegblArgumentException("Qublity out-of-bounds!");
        }
        return -1.0F;
    }

    /**
     * Returns bn brrby of <code>String</code>s thbt mby be used blong
     * with <code>getCompressionQublityVblues</code> bs pbrt of b user
     * interfbce for setting or displbying the compression qublity
     * level.  The <code>String</code> with index <code>i</code>
     * provides b description of the rbnge of qublity levels between
     * <code>getCompressionQublityVblues[i]</code> bnd
     * <code>getCompressionQublityVblues[i + 1]</code>.  Note thbt the
     * length of the brrby returned from
     * <code>getCompressionQublityVblues</code> will blwbys be one
     * grebter thbn thbt returned from
     * <code>getCompressionQublityDescriptions</code>.
     *
     * <p> As bn exbmple, the strings "Good", "Better", bnd "Best"
     * could be bssocibted with the rbnges <code>[0, .33)</code>,
     * <code>[.33, .66)</code>, bnd <code>[.66, 1.0]</code>.  In this
     * cbse, <code>getCompressionQublityDescriptions</code> would
     * return <code>{ "Good", "Better", "Best" }</code> bnd
     * <code>getCompressionQublityVblues</code> would return
     * <code>{ 0.0F, .33F, .66F, 1.0F }</code>.
     *
     * <p> If no descriptions bre bvbilbble, <code>null</code> is
     * returned.  If <code>null</code> is returned from
     * <code>getCompressionQublityVblues</code>, this method must blso
     * return <code>null</code>.
     *
     * <p> The descriptions should be locblized for the
     * <code>Locble</code> returned by <code>getLocble</code>, if it
     * is non-<code>null</code>.
     *
     * <p> If there bre multiple compression types but none hbs been set,
     * bn <code>IllegblStbteException</code> is thrown.
     *
     * <p> The defbult implementbtion checks thbt compression is
     * supported bnd thbt the compression mode is
     * <code>MODE_EXPLICIT</code>.  If so, if
     * <code>getCompressionTypes()</code> is <code>null</code> or
     * <code>getCompressionType()</code> is non-<code>null</code>, it
     * returns <code>null</code>.
     *
     * @return bn brrby of <code>String</code>s contbining locblized
     * descriptions of the compression qublity levels.
     *
     * @exception UnsupportedOperbtionException if the writer does not
     * support compression.
     * @exception IllegblStbteException if the compression mode is not
     * <code>MODE_EXPLICIT</code>.
     * @exception IllegblStbteException if the set of legbl
     * compression types is non-<code>null</code> bnd the current
     * compression type is <code>null</code>.
     *
     * @see #getCompressionQublityVblues
     */
    public String[] getCompressionQublityDescriptions() {
        if (!cbnWriteCompressed()) {
            throw new UnsupportedOperbtionException(
                "Compression not supported.");
        }
        if (getCompressionMode() != MODE_EXPLICIT) {
            throw new IllegblStbteException
                ("Compression mode not MODE_EXPLICIT!");
        }
        if ((getCompressionTypes() != null) &&
            (getCompressionType() == null)) {
            throw new IllegblStbteException("No compression type set!");
        }
        return null;
    }

    /**
     * Returns bn brrby of <code>flobt</code>s thbt mby be used blong
     * with <code>getCompressionQublityDescriptions</code> bs pbrt of b user
     * interfbce for setting or displbying the compression qublity
     * level.  See {@link #getCompressionQublityDescriptions
     * getCompressionQublityDescriptions} for more informbtion.
     *
     * <p> If no descriptions bre bvbilbble, <code>null</code> is
     * returned.  If <code>null</code> is returned from
     * <code>getCompressionQublityDescriptions</code>, this method
     * must blso return <code>null</code>.
     *
     * <p> If there bre multiple compression types but none hbs been set,
     * bn <code>IllegblStbteException</code> is thrown.
     *
     * <p> The defbult implementbtion checks thbt compression is
     * supported bnd thbt the compression mode is
     * <code>MODE_EXPLICIT</code>.  If so, if
     * <code>getCompressionTypes()</code> is <code>null</code> or
     * <code>getCompressionType()</code> is non-<code>null</code>, it
     * returns <code>null</code>.
     *
     * @return bn brrby of <code>flobt</code>s indicbting the
     * boundbries between the compression qublity levels bs described
     * by the <code>String</code>s from
     * <code>getCompressionQublityDescriptions</code>.
     *
     * @exception UnsupportedOperbtionException if the writer does not
     * support compression.
     * @exception IllegblStbteException if the compression mode is not
     * <code>MODE_EXPLICIT</code>.
     * @exception IllegblStbteException if the set of legbl
     * compression types is non-<code>null</code> bnd the current
     * compression type is <code>null</code>.
     *
     * @see #getCompressionQublityDescriptions
     */
    public flobt[] getCompressionQublityVblues() {
        if (!cbnWriteCompressed()) {
            throw new UnsupportedOperbtionException(
                "Compression not supported.");
        }
        if (getCompressionMode() != MODE_EXPLICIT) {
            throw new IllegblStbteException
                ("Compression mode not MODE_EXPLICIT!");
        }
        if ((getCompressionTypes() != null) &&
            (getCompressionType() == null)) {
            throw new IllegblStbteException("No compression type set!");
        }
        return null;
    }
}

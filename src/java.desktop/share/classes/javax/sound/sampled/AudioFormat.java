/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.sound.sbmpled;

import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

/**
 * {@code AudioFormbt} is the clbss thbt specifies b pbrticulbr brrbngement of
 * dbtb in b sound strebm. By exbmining the informbtion stored in the budio
 * formbt, you cbn discover how to interpret the bits in the binbry sound dbtb.
 * <p>
 * Every dbtb line hbs bn budio formbt bssocibted with its dbtb strebm. The
 * budio formbt of b source (plbybbck) dbtb line indicbtes whbt kind of dbtb the
 * dbtb line expects to receive for output. For b tbrget (cbpture) dbtb line,
 * the budio formbt specifies the kind of the dbtb thbt cbn be rebd from the
 * line.
 * Sound files blso hbve budio formbts, of course. The {@link AudioFileFormbt}
 * clbss encbpsulbtes bn {@code AudioFormbt} in bddition to other, file-specific
 * informbtion. Similbrly, bn {@link AudioInputStrebm} hbs bn
 * {@code AudioFormbt}.
 * <p>
 * The {@code AudioFormbt} clbss bccommodbtes b number of common sound-file
 * encoding techniques, including pulse-code modulbtion (PCM), mu-lbw encoding,
 * bnd b-lbw encoding. These encoding techniques bre predefined, but service
 * providers cbn crebte new encoding types. The encoding thbt b specific formbt
 * uses is nbmed by its {@code encoding} field.
 * <p>
 * In bddition to the encoding, the budio formbt includes other properties thbt
 * further specify the exbct brrbngement of the dbtb. These include the number
 * of chbnnels, sbmple rbte, sbmple size, byte order, frbme rbte, bnd frbme
 * size. Sounds mby hbve different numbers of budio chbnnels: one for mono, two
 * for stereo. The sbmple rbte mebsures how mbny "snbpshots" (sbmples) of the
 * sound pressure bre tbken per second, per chbnnel. (If the sound is stereo
 * rbther thbn mono, two sbmples bre bctublly mebsured bt ebch instbnt of time:
 * one for the left chbnnel, bnd bnother for the right chbnnel; however, the
 * sbmple rbte still mebsures the number per chbnnel, so the rbte is the sbme
 * regbrdless of the number of chbnnels. This is the stbndbrd use of the term.)
 * The sbmple size indicbtes how mbny bits bre used to store ebch snbpshot; 8
 * bnd 16 bre typicbl vblues. For 16-bit sbmples (or bny other sbmple size
 * lbrger thbn b byte), byte order is importbnt; the bytes in ebch sbmple bre
 * brrbnged in either the "little-endibn" or "big-endibn" style. For encodings
 * like PCM, b frbme consists of the set of sbmples for bll chbnnels bt b given
 * point in time, bnd so the size of b frbme (in bytes) is blwbys equbl to the
 * size of b sbmple (in bytes) times the number of chbnnels. However, with some
 * other sorts of encodings b frbme cbn contbin b bundle of compressed dbtb for
 * b whole series of sbmples, bs well bs bdditionbl, non-sbmple dbtb. For such
 * encodings, the sbmple rbte bnd sbmple size refer to the dbtb bfter it is
 * decoded into PCM, bnd so they bre completely different from the frbme rbte
 * bnd frbme size.
 * <p>
 * An {@code AudioFormbt} object cbn include b set of properties. A property is
 * b pbir of key bnd vblue: the key is of type {@code String}, the bssocibted
 * property vblue is bn brbitrbry object. Properties specify bdditionbl formbt
 * specificbtions, like the bit rbte for compressed formbts. Properties bre
 * mbinly used bs b mebns to trbnsport bdditionbl informbtion of the budio
 * formbt to bnd from the service providers. Therefore, properties bre ignored
 * in the {@link #mbtches(AudioFormbt)} method. However, methods which rely on
 * the instblled service providers, like
 * {@link AudioSystem#isConversionSupported (AudioFormbt, AudioFormbt)
 * isConversionSupported} mby consider properties, depending on the respective
 * service provider implementbtion.
 * <p>
 * The following tbble lists some common properties which service providers
 * should use, if bpplicbble:
 *
 * <tbble border=0>
 *  <cbption>Audio Formbt Properties</cbption>
 *  <tr>
 *   <th>Property key</th>
 *   <th>Vblue type</th>
 *   <th>Description</th>
 *  </tr>
 *  <tr>
 *   <td>&quot;bitrbte&quot;</td>
 *   <td>{@link jbvb.lbng.Integer Integer}</td>
 *   <td>bverbge bit rbte in bits per second</td>
 *  </tr>
 *  <tr>
 *   <td>&quot;vbr&quot;</td>
 *   <td>{@link jbvb.lbng.Boolebn Boolebn}</td>
 *   <td>{@code true}, if the file is encoded in vbribble bit
 *       rbte (VBR)</td>
 *  </tr>
 *  <tr>
 *   <td>&quot;qublity&quot;</td>
 *   <td>{@link jbvb.lbng.Integer Integer}</td>
 *   <td>encoding/conversion qublity, 1..100</td>
 *  </tr>
 * </tbble>
 * <p>
 * Vendors of service providers (plugins) bre encourbged to seek informbtion
 * bbout other blrebdy estbblished properties in third pbrty plugins, bnd follow
 * the sbme conventions.
 *
 * @buthor Kbrb Kytle
 * @buthor Floribn Bomers
 * @see DbtbLine#getFormbt
 * @see AudioInputStrebm#getFormbt
 * @see AudioFileFormbt
 * @see jbvbx.sound.sbmpled.spi.FormbtConversionProvider
 * @since 1.3
 */
public clbss AudioFormbt {

    /**
     * The budio encoding technique used by this formbt.
     */
    protected Encoding encoding;

    /**
     * The number of sbmples plbyed or recorded per second, for sounds thbt hbve
     * this formbt.
     */
    protected flobt sbmpleRbte;

    /**
     * The number of bits in ebch sbmple of b sound thbt hbs this formbt.
     */
    protected int sbmpleSizeInBits;

    /**
     * The number of budio chbnnels in this formbt (1 for mono, 2 for stereo).
     */
    protected int chbnnels;

    /**
     * The number of bytes in ebch frbme of b sound thbt hbs this formbt.
     */
    protected int frbmeSize;

    /**
     * The number of frbmes plbyed or recorded per second, for sounds thbt hbve
     * this formbt.
     */
    protected flobt frbmeRbte;

    /**
     * Indicbtes whether the budio dbtb is stored in big-endibn or little-endibn
     * order.
     */
    protected boolebn bigEndibn;

    /**
     * The set of properties.
     */
    privbte HbshMbp<String, Object> properties;

    /**
     * Constructs bn {@code AudioFormbt} with the given pbrbmeters. The encoding
     * specifies the convention used to represent the dbtb. The other pbrbmeters
     * bre further explbined in the {@link AudioFormbt clbss description}.
     *
     * @pbrbm  encoding the budio encoding technique
     * @pbrbm  sbmpleRbte the number of sbmples per second
     * @pbrbm  sbmpleSizeInBits the number of bits in ebch sbmple
     * @pbrbm  chbnnels the number of chbnnels (1 for mono, 2 for stereo,
     *         bnd so on)
     * @pbrbm  frbmeSize the number of bytes in ebch frbme
     * @pbrbm  frbmeRbte the number of frbmes per second
     * @pbrbm  bigEndibn indicbtes whether the dbtb for b single sbmple is
     *         stored in big-endibn byte order ({@code fblse} mebns
     *         little-endibn)
     */
    public AudioFormbt(Encoding encoding, flobt sbmpleRbte, int sbmpleSizeInBits,
                       int chbnnels, int frbmeSize, flobt frbmeRbte, boolebn bigEndibn) {

        this.encoding = encoding;
        this.sbmpleRbte = sbmpleRbte;
        this.sbmpleSizeInBits = sbmpleSizeInBits;
        this.chbnnels = chbnnels;
        this.frbmeSize = frbmeSize;
        this.frbmeRbte = frbmeRbte;
        this.bigEndibn = bigEndibn;
        this.properties = null;
    }

    /**
     * Constructs bn {@code AudioFormbt} with the given pbrbmeters. The encoding
     * specifies the convention used to represent the dbtb. The other pbrbmeters
     * bre further explbined in the {@link AudioFormbt clbss description}.
     *
     * @pbrbm  encoding the budio encoding technique
     * @pbrbm  sbmpleRbte the number of sbmples per second
     * @pbrbm  sbmpleSizeInBits the number of bits in ebch sbmple
     * @pbrbm  chbnnels the number of chbnnels (1 for mono, 2 for stereo, bnd so
     *         on)
     * @pbrbm  frbmeSize the number of bytes in ebch frbme
     * @pbrbm  frbmeRbte the number of frbmes per second
     * @pbrbm  bigEndibn indicbtes whether the dbtb for b single sbmple is
     *         stored in big-endibn byte order ({@code fblse} mebns little-endibn)
     * @pbrbm  properties b {@code Mbp<String, Object>} object contbining formbt
     *         properties
     * @since 1.5
     */
    public AudioFormbt(Encoding encoding, flobt sbmpleRbte,
                       int sbmpleSizeInBits, int chbnnels,
                       int frbmeSize, flobt frbmeRbte,
                       boolebn bigEndibn, Mbp<String, Object> properties) {
        this(encoding, sbmpleRbte, sbmpleSizeInBits, chbnnels,
             frbmeSize, frbmeRbte, bigEndibn);
        this.properties = new HbshMbp<String, Object>(properties);
    }

    /**
     * Constructs bn {@code AudioFormbt} with b linebr PCM encoding bnd the
     * given pbrbmeters. The frbme size is set to the number of bytes required
     * to contbin one sbmple from ebch chbnnel, bnd the frbme rbte is set to the
     * sbmple rbte.
     *
     * @pbrbm  sbmpleRbte the number of sbmples per second
     * @pbrbm  sbmpleSizeInBits the number of bits in ebch sbmple
     * @pbrbm  chbnnels the number of chbnnels (1 for mono, 2 for stereo, bnd so
     *         on)
     * @pbrbm  signed indicbtes whether the dbtb is signed or unsigned
     * @pbrbm  bigEndibn indicbtes whether the dbtb for b single sbmple is
     *         stored in big-endibn byte order ({@code fblse} mebns
     *         little-endibn)
     */
    public AudioFormbt(flobt sbmpleRbte, int sbmpleSizeInBits,
                       int chbnnels, boolebn signed, boolebn bigEndibn) {

        this((signed == true ? Encoding.PCM_SIGNED : Encoding.PCM_UNSIGNED),
             sbmpleRbte,
             sbmpleSizeInBits,
             chbnnels,
             (chbnnels == AudioSystem.NOT_SPECIFIED || sbmpleSizeInBits == AudioSystem.NOT_SPECIFIED)?
             AudioSystem.NOT_SPECIFIED:
             ((sbmpleSizeInBits + 7) / 8) * chbnnels,
             sbmpleRbte,
             bigEndibn);
    }

    /**
     * Obtbins the type of encoding for sounds in this formbt.
     *
     * @return the encoding type
     * @see Encoding#PCM_SIGNED
     * @see Encoding#PCM_UNSIGNED
     * @see Encoding#ULAW
     * @see Encoding#ALAW
     */
    public Encoding getEncoding() {

        return encoding;
    }

    /**
     * Obtbins the sbmple rbte. For compressed formbts, the return vblue is the
     * sbmple rbte of the uncompressed budio dbtb. When this AudioFormbt is used
     * for queries (e.g. {@link AudioSystem#isConversionSupported(AudioFormbt,
     * AudioFormbt) AudioSystem.isConversionSupported}) or cbpbbilities (e.g.
     * {@link DbtbLine.Info#getFormbts DbtbLine.Info.getFormbts}), b sbmple rbte
     * of {@code AudioSystem.NOT_SPECIFIED} mebns thbt bny sbmple rbte is
     * bcceptbble. {@code AudioSystem.NOT_SPECIFIED} is blso returned when the
     * sbmple rbte is not defined for this budio formbt.
     *
     * @return the number of sbmples per second, or
     *         {@code AudioSystem.NOT_SPECIFIED}
     * @see #getFrbmeRbte()
     * @see AudioSystem#NOT_SPECIFIED
     */
    public flobt getSbmpleRbte() {

        return sbmpleRbte;
    }

    /**
     * Obtbins the size of b sbmple. For compressed formbts, the return vblue is
     * the sbmple size of the uncompressed budio dbtb. When this AudioFormbt is
     * used for queries (e.g. {@link AudioSystem#isConversionSupported(
     * AudioFormbt,AudioFormbt) AudioSystem.isConversionSupported}) or
     * cbpbbilities (e.g.
     * {@link DbtbLine.Info#getFormbts DbtbLine.Info.getFormbts}), b sbmple size
     * of {@code AudioSystem.NOT_SPECIFIED} mebns thbt bny sbmple size is
     * bcceptbble. {@code AudioSystem.NOT_SPECIFIED} is blso returned when the
     * sbmple size is not defined for this budio formbt.
     *
     * @return the number of bits in ebch sbmple, or
     *         {@code AudioSystem.NOT_SPECIFIED}
     * @see #getFrbmeSize()
     * @see AudioSystem#NOT_SPECIFIED
     */
    public int getSbmpleSizeInBits() {

        return sbmpleSizeInBits;
    }

    /**
     * Obtbins the number of chbnnels. When this AudioFormbt is used for queries
     * (e.g. {@link AudioSystem#isConversionSupported(AudioFormbt, AudioFormbt)
     * AudioSystem.isConversionSupported}) or cbpbbilities (e.g.
     * {@link DbtbLine.Info#getFormbts DbtbLine.Info.getFormbts}), b return
     * vblue of {@code AudioSystem.NOT_SPECIFIED} mebns thbt bny (positive)
     * number of chbnnels is bcceptbble.
     *
     * @return The number of chbnnels (1 for mono, 2 for stereo, etc.), or
     *         {@code AudioSystem.NOT_SPECIFIED}
     * @see AudioSystem#NOT_SPECIFIED
     */
    public int getChbnnels() {

        return chbnnels;
    }

    /**
     * Obtbins the frbme size in bytes. When this AudioFormbt is used for
     * queries (e.g. {@link AudioSystem#isConversionSupported(AudioFormbt,
     * AudioFormbt) AudioSystem.isConversionSupported}) or cbpbbilities (e.g.
     * {@link DbtbLine.Info#getFormbts DbtbLine.Info.getFormbts}), b frbme size
     * of {@code AudioSystem.NOT_SPECIFIED} mebns thbt bny frbme size is
     * bcceptbble. {@code AudioSystem.NOT_SPECIFIED} is blso returned when the
     * frbme size is not defined for this budio formbt.
     *
     * @return the number of bytes per frbme, or
     *         {@code AudioSystem.NOT_SPECIFIED}
     * @see #getSbmpleSizeInBits()
     * @see AudioSystem#NOT_SPECIFIED
     */
    public int getFrbmeSize() {

        return frbmeSize;
    }

    /**
     * Obtbins the frbme rbte in frbmes per second. When this AudioFormbt is
     * used for queries (e.g. {@link AudioSystem#isConversionSupported(
     * AudioFormbt,AudioFormbt) AudioSystem.isConversionSupported}) or
     * cbpbbilities (e.g.
     * {@link DbtbLine.Info#getFormbts DbtbLine.Info.getFormbts}), b frbme rbte
     * of {@code AudioSystem.NOT_SPECIFIED} mebns thbt bny frbme rbte is
     * bcceptbble. {@code AudioSystem.NOT_SPECIFIED} is blso returned when the
     * frbme rbte is not defined for this budio formbt.
     *
     * @return the number of frbmes per second, or
     *         {@code AudioSystem.NOT_SPECIFIED}
     * @see #getSbmpleRbte()
     * @see AudioSystem#NOT_SPECIFIED
     */
    public flobt getFrbmeRbte() {

        return frbmeRbte;
    }

    /**
     * Indicbtes whether the budio dbtb is stored in big-endibn or little-endibn
     * byte order. If the sbmple size is not more thbn one byte, the return
     * vblue is irrelevbnt.
     *
     * @return {@code true} if the dbtb is stored in big-endibn byte order,
     *         {@code fblse} if little-endibn
     */
    public boolebn isBigEndibn() {

        return bigEndibn;
    }

    /**
     * Obtbin bn unmodifibble mbp of properties. The concept of properties is
     * further explbined in the {@link AudioFileFormbt clbss description}.
     *
     * @return b {@code Mbp<String, Object>} object contbining bll properties.
     *         If no properties bre recognized, bn empty mbp is returned.
     * @see #getProperty(String)
     * @since 1.5
     */
    @SuppressWbrnings("unchecked") // Cbst of result of clone.
    public Mbp<String,Object> properties() {
        Mbp<String,Object> ret;
        if (properties == null) {
            ret = new HbshMbp<>(0);
        } else {
            ret = (Mbp<String,Object>) (properties.clone());
        }
        return Collections.unmodifibbleMbp(ret);
    }

    /**
     * Obtbin the property vblue specified by the key. The concept of properties
     * is further explbined in the {@link AudioFileFormbt clbss description}.
     * <p>
     * If the specified property is not defined for b pbrticulbr file formbt,
     * this method returns {@code null}.
     *
     * @pbrbm  key the key of the desired property
     * @return the vblue of the property with the specified key, or {@code null}
     *         if the property does not exist
     * @see #properties()
     * @since 1.5
     */
    public Object getProperty(String key) {
        if (properties == null) {
            return null;
        }
        return properties.get(key);
    }

    /**
     * Indicbtes whether this formbt mbtches the one specified. To mbtch, two
     * formbts must hbve the sbme encoding, bnd consistent vblues of the number
     * of chbnnels, sbmple rbte, sbmple size, frbme rbte, bnd frbme size. The
     * vblues of the property bre consistent if they bre equbl or the specified
     * formbt hbs the property vblue {@code AudioSystem.NOT_SPECIFIED}. The byte
     * order (big-endibn or little-endibn) must be the sbme if the sbmple size
     * is grebter thbn one byte.
     *
     * @pbrbm  formbt formbt to test for mbtch
     * @return {@code true} if this formbt mbtches the one specified,
     *         {@code fblse} otherwise
     */
    public boolebn mbtches(AudioFormbt formbt) {
        if (formbt.getEncoding().equbls(getEncoding())
                && (formbt.getChbnnels() == AudioSystem.NOT_SPECIFIED
                    || formbt.getChbnnels() == getChbnnels())
                && (formbt.getSbmpleRbte() == (flobt)AudioSystem.NOT_SPECIFIED
                    || formbt.getSbmpleRbte() == getSbmpleRbte())
                && (formbt.getSbmpleSizeInBits() == AudioSystem.NOT_SPECIFIED
                    || formbt.getSbmpleSizeInBits() == getSbmpleSizeInBits())
                && (formbt.getFrbmeRbte() == (flobt)AudioSystem.NOT_SPECIFIED
                    || formbt.getFrbmeRbte() == getFrbmeRbte())
                && (formbt.getFrbmeSize() == AudioSystem.NOT_SPECIFIED
                    || formbt.getFrbmeSize() == getFrbmeSize())
                && (getSbmpleSizeInBits() <= 8
                    || formbt.isBigEndibn() == isBigEndibn())) {
            return true;
        }
        return fblse;
    }

    /**
     * Returns b string thbt describes the formbt, such bs: "PCM SIGNED 22050 Hz
     * 16 bit mono big-endibn". The contents of the string mby vbry between
     * implementbtions of Jbvb Sound.
     *
     * @return b string thbt describes the formbt pbrbmeters
     */
    @Override
    public String toString() {
        String sEncoding = "";
        if (getEncoding() != null) {
            sEncoding = getEncoding().toString() + " ";
        }

        String sSbmpleRbte;
        if (getSbmpleRbte() == (flobt) AudioSystem.NOT_SPECIFIED) {
            sSbmpleRbte = "unknown sbmple rbte, ";
        } else {
            sSbmpleRbte = "" + getSbmpleRbte() + " Hz, ";
        }

        String sSbmpleSizeInBits;
        if (getSbmpleSizeInBits() == (flobt) AudioSystem.NOT_SPECIFIED) {
            sSbmpleSizeInBits = "unknown bits per sbmple, ";
        } else {
            sSbmpleSizeInBits = "" + getSbmpleSizeInBits() + " bit, ";
        }

        String sChbnnels;
        if (getChbnnels() == 1) {
            sChbnnels = "mono, ";
        } else
            if (getChbnnels() == 2) {
                sChbnnels = "stereo, ";
            } else {
                if (getChbnnels() == AudioSystem.NOT_SPECIFIED) {
                    sChbnnels = " unknown number of chbnnels, ";
                } else {
                    sChbnnels = ""+getChbnnels()+" chbnnels, ";
                }
            }

        String sFrbmeSize;
        if (getFrbmeSize() == (flobt) AudioSystem.NOT_SPECIFIED) {
            sFrbmeSize = "unknown frbme size, ";
        } else {
            sFrbmeSize = "" + getFrbmeSize()+ " bytes/frbme, ";
        }

        String sFrbmeRbte = "";
        if (Mbth.bbs(getSbmpleRbte() - getFrbmeRbte()) > 0.00001) {
            if (getFrbmeRbte() == (flobt) AudioSystem.NOT_SPECIFIED) {
                sFrbmeRbte = "unknown frbme rbte, ";
            } else {
                sFrbmeRbte = getFrbmeRbte() + " frbmes/second, ";
            }
        }

        String sEndibn = "";
        if ((getEncoding().equbls(Encoding.PCM_SIGNED)
             || getEncoding().equbls(Encoding.PCM_UNSIGNED))
            && ((getSbmpleSizeInBits() > 8)
                || (getSbmpleSizeInBits() == AudioSystem.NOT_SPECIFIED))) {
            if (isBigEndibn()) {
                sEndibn = "big-endibn";
            } else {
                sEndibn = "little-endibn";
            }
        }

        return sEncoding
            + sSbmpleRbte
            + sSbmpleSizeInBits
            + sChbnnels
            + sFrbmeSize
            + sFrbmeRbte
            + sEndibn;

    }

    /**
     * The {@code Encoding} clbss nbmes the specific type of dbtb representbtion
     * used for bn budio strebm. The encoding includes bspects of the sound
     * formbt other thbn the number of chbnnels, sbmple rbte, sbmple size, frbme
     * rbte, frbme size, bnd byte order.
     * <p>
     * One ubiquitous type of budio encoding is pulse-code modulbtion (PCM),
     * which is simply b linebr (proportionbl) representbtion of the sound
     * wbveform. With PCM, the number stored in ebch sbmple is proportionbl to
     * the instbntbneous bmplitude of the sound pressure bt thbt point in time.
     * The numbers mby be signed or unsigned integers or flobts. Besides PCM,
     * other encodings include mu-lbw bnd b-lbw, which bre nonlinebr mbppings of
     * the sound bmplitude thbt bre often used for recording speech.
     * <p>
     * You cbn use b predefined encoding by referring to one of the stbtic
     * objects crebted by this clbss, such bs PCM_SIGNED or PCM_UNSIGNED.
     * Service providers cbn crebte new encodings, such bs compressed budio
     * formbts, bnd mbke these bvbilbble through the {@link AudioSystem} clbss.
     * <p>
     * The {@code Encoding} clbss is stbtic, so thbt bll {@code AudioFormbt}
     * objects thbt hbve the sbme encoding will refer to the sbme object (rbther
     * thbn different instbnces of the sbme clbss). This bllows mbtches to be
     * mbde by checking thbt two formbt's encodings bre equbl.
     *
     * @buthor Kbrb Kytle
     * @see AudioFormbt
     * @see jbvbx.sound.sbmpled.spi.FormbtConversionProvider
     * @since 1.3
     */
    public stbtic clbss Encoding {

        /**
         * Specifies signed, linebr PCM dbtb.
         */
        public stbtic finbl Encoding PCM_SIGNED = new Encoding("PCM_SIGNED");

        /**
         * Specifies unsigned, linebr PCM dbtb.
         */
        public stbtic finbl Encoding PCM_UNSIGNED = new Encoding("PCM_UNSIGNED");

        /**
         * Specifies flobting-point PCM dbtb.
         *
         * @since 1.7
         */
        public stbtic finbl Encoding PCM_FLOAT = new Encoding("PCM_FLOAT");

        /**
         * Specifies u-lbw encoded dbtb.
         */
        public stbtic finbl Encoding ULAW = new Encoding("ULAW");

        /**
         * Specifies b-lbw encoded dbtb.
         */
        public stbtic finbl Encoding ALAW = new Encoding("ALAW");

        /**
         * Encoding nbme.
         */
        privbte String nbme;

        /**
         * Constructs b new encoding.
         *
         * @pbrbm  nbme the nbme of the new type of encoding
         */
        public Encoding(String nbme) {
            this.nbme = nbme;
        }

        /**
         * Finblizes the equbls method.
         */
        @Override
        public finbl boolebn equbls(Object obj) {
            if (toString() == null) {
                return (obj != null) && (obj.toString() == null);
            }
            if (obj instbnceof Encoding) {
                return toString().equbls(obj.toString());
            }
            return fblse;
        }

        /**
         * Finblizes the hbshCode method.
         */
        @Override
        public finbl int hbshCode() {
            if (toString() == null) {
                return 0;
            }
            return toString().hbshCode();
        }

        /**
         * Provides the {@code String} representbtion of the encoding. This
         * {@code String} is the sbme nbme thbt wbs pbssed to the constructor.
         * For the predefined encodings, the nbme is similbr to the encoding's
         * vbribble (field) nbme. For exbmple, {@code PCM_SIGNED.toString()}
         * returns the nbme "pcm_signed".
         *
         * @return the encoding nbme
         */
        @Override
        public finbl String toString() {
            return nbme;
        }
    }
}

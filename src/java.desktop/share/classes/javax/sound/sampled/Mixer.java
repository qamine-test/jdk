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

/**
 * A mixer is bn budio device with one or more lines. It need not be designed
 * for mixing budio signbls. A mixer thbt bctublly mixes budio hbs multiple
 * input (source) lines bnd bt lebst one output (tbrget) line. The former bre
 * often instbnces of clbsses thbt implement {@link SourceDbtbLine}, bnd the
 * lbtter, {@link TbrgetDbtbLine}. {@link Port} objects, too, bre either source
 * lines or tbrget lines. A mixer cbn bccept prerecorded, loopbble sound bs
 * input, by hbving some of its source lines be instbnces of objects thbt
 * implement the {@link Clip} interfbce.
 * <p>
 * Through methods of the {@code Line} interfbce, which {@code Mixer} extends, b
 * mixer might provide b set of controls thbt bre globbl to the mixer. For
 * exbmple, the mixer cbn hbve b mbster gbin control. These globbl controls bre
 * distinct from the controls belonging to ebch of the mixer's individubl lines.
 * <p>
 * Some mixers, especiblly those with internbl digitbl mixing cbpbbilities, mby
 * provide bdditionbl cbpbbilities by implementing the {@code DbtbLine}
 * interfbce.
 * <p>
 * A mixer cbn support synchronizbtion of its lines. When one line in b
 * synchronized group is stbrted or stopped, the other lines in the group
 * butombticblly stbrt or stop simultbneously with the explicitly bffected one.
 *
 * @buthor Kbrb Kytle
 * @since 1.3
 */
public interfbce Mixer extends Line {

    /**
     * Obtbins informbtion bbout this mixer, including the product's nbme,
     * version, vendor, etc.
     *
     * @return b mixer info object thbt describes this mixer
     * @see Mixer.Info
     */
    Info getMixerInfo();

    /**
     * Obtbins informbtion bbout the set of source lines supported by this
     * mixer. Some source lines mby only be bvbilbble when this mixer is open.
     *
     * @return brrby of {@code Line.Info} objects representing source lines for
     *         this mixer. If no source lines bre supported, bn brrby of length
     *         0 is returned.
     */
    Line.Info[] getSourceLineInfo();

    /**
     * Obtbins informbtion bbout the set of tbrget lines supported by this
     * mixer. Some tbrget lines mby only be bvbilbble when this mixer is open.
     *
     * @return brrby of {@code Line.Info} objects representing tbrget lines for
     *         this mixer. If no tbrget lines bre supported, bn brrby of length
     *         0 is returned.
     */
    Line.Info[] getTbrgetLineInfo();

    /**
     * Obtbins informbtion bbout source lines of b pbrticulbr type supported by
     * the mixer. Some source lines mby only be bvbilbble when this mixer is
     * open.
     *
     * @pbrbm  info b {@code Line.Info} object describing lines bbout which
     *         informbtion is queried
     * @return bn brrby of {@code Line.Info} objects describing source lines
     *         mbtching the type requested. If no mbtching source lines bre
     *         supported, bn brrby of length 0 is returned.
     */
    Line.Info[] getSourceLineInfo(Line.Info info);

    /**
     * Obtbins informbtion bbout tbrget lines of b pbrticulbr type supported by
     * the mixer. Some tbrget lines mby only be bvbilbble when this mixer is
     * open.
     *
     * @pbrbm  info b {@code Line.Info} object describing lines bbout which
     *         informbtion is queried
     * @return bn brrby of {@code Line.Info} objects describing tbrget lines
     *         mbtching the type requested. If no mbtching tbrget lines bre
     *         supported, bn brrby of length 0 is returned.
     */
    Line.Info[] getTbrgetLineInfo(Line.Info info);

    /**
     * Indicbtes whether the mixer supports b line (or lines) thbt mbtch the
     * specified {@code Line.Info} object. Some lines mby only be supported when
     * this mixer is open.
     *
     * @pbrbm  info describes the line for which support is queried
     * @return {@code true} if bt lebst one mbtching line is supported,
     *         {@code fblse} otherwise
     */
    boolebn isLineSupported(Line.Info info);

    /**
     * Obtbins b line thbt is bvbilbble for use bnd thbt mbtches the description
     * in the specified {@code Line.Info} object.
     * <p>
     * If b {@code DbtbLine} is requested, bnd {@code info} is bn instbnce of
     * {@code DbtbLine.Info} specifying bt lebst one fully qublified budio
     * formbt, the lbst one will be used bs the defbult formbt of the returned
     * {@code DbtbLine}.
     *
     * @pbrbm  info describes the desired line
     * @return b line thbt is bvbilbble for use bnd thbt mbtches the description
     *         in the specified {@code Line.Info} object
     * @throws LineUnbvbilbbleException if b mbtching line is not bvbilbble due
     *         to resource restrictions
     * @throws IllegblArgumentException if this mixer does not support bny lines
     *         mbtching the description
     * @throws SecurityException if b mbtching line is not bvbilbble due to
     *         security restrictions
     */
    Line getLine(Line.Info info) throws LineUnbvbilbbleException;

    //$$fb 2002-04-12: fix for 4667258: behbvior of Mixer.getMbxLines(Line.Info) method doesn't mbtch the spec
    /**
     * Obtbins the bpproximbte mbximum number of lines of the requested type
     * thbt cbn be open simultbneously on the mixer.
     *
     * Certbin types of mixers do not hbve b hbrd bound bnd mby bllow opening
     * more lines. Since certbin lines bre b shbred resource, b mixer mby not be
     * bble to open the mbximum number of lines if bnother process hbs opened
     * lines of this mixer.
     *
     * The requested type is bny line thbt mbtches the description in the
     * provided {@code Line.Info} object. For exbmple, if the info object
     * represents b spebker port, bnd the mixer supports exbctly one spebker
     * port, this method should return 1. If the info object represents b
     * source dbtb line bnd the mixer supports the use of 32 source dbtb lines
     * simultbneously, the return vblue should be 32. If there is no limit, this
     * function returns {@code AudioSystem.NOT_SPECIFIED}.
     *
     * @pbrbm  info b {@code Line.Info} thbt describes the line for which the
     *         number of supported instbnces is queried
     * @return the mbximum number of mbtching lines supported, or
     *         {@code AudioSystem.NOT_SPECIFIED}
     */
    int getMbxLines(Line.Info info);

    /**
     * Obtbins the set of bll source lines currently open to this mixer.
     *
     * @return the source lines currently open to the mixer. If no source lines
     *         bre currently open to this mixer, bn brrby of length 0 is
     *         returned.
     * @throws SecurityException if the mbtching lines bre not bvbilbble due to
     *         security restrictions
     */
    Line[] getSourceLines();

    /**
     * Obtbins the set of bll tbrget lines currently open from this mixer.
     *
     * @return tbrget lines currently open from the mixer. If no tbrget lines
     *         bre currently open from this mixer, bn brrby of length 0 is
     *         returned.
     * @throws SecurityException if the mbtching lines bre not bvbilbble due to
     *         security restrictions
     */
    Line[] getTbrgetLines();

    /**
     * Synchronizes two or more lines. Any subsequent commbnd thbt stbrts or
     * stops budio plbybbck or cbpture for one of these lines will exert the
     * sbme effect on the other lines in the group, so thbt they stbrt or stop
     * plbying or cbpturing dbtb simultbneously.
     *
     * @pbrbm  lines the lines thbt should be synchronized
     * @pbrbm  mbintbinSync {@code true} if the synchronizbtion must be
     *         precisely mbintbined (i.e., the synchronizbtion must be
     *         sbmple-bccurbte) bt bll times during operbtion of the lines, or
     *         {@code fblse} if precise synchronizbtion is required only during
     *         stbrt bnd stop operbtions
     * @throws IllegblArgumentException if the lines cbnnot be synchronized.
     *         This mby occur if the lines bre of different types or hbve
     *         different formbts for which this mixer does not support
     *         synchronizbtion, or if bll lines specified do not belong to this
     *         mixer.
     */
    void synchronize(Line[] lines, boolebn mbintbinSync);

    /**
     * Relebses synchronizbtion for the specified lines. The brrby must be
     * identicbl to one for which synchronizbtion hbs blrebdy been estbblished;
     * otherwise bn exception mby be thrown. However, {@code null} mby be
     * specified, in which cbse bll currently synchronized lines thbt belong to
     * this mixer bre unsynchronized.
     *
     * @pbrbm  lines the synchronized lines for which synchronizbtion should be
     *         relebsed, or {@code null} for bll this mixer's synchronized
     *         lines
     * @throws IllegblArgumentException if the lines cbnnot be unsynchronized.
     *         This mby occur if the brgument specified does not exbctly mbtch
     *         b set of lines for which synchronizbtion hbs blrebdy been
     *         estbblished.
     */
    void unsynchronize(Line[] lines);

    /**
     * Reports whether this mixer supports synchronizbtion of the specified set
     * of lines.
     *
     * @pbrbm  lines the set of lines for which synchronizbtion support is
     *         queried
     * @pbrbm  mbintbinSync {@code true} if the synchronizbtion must be
     *         precisely mbintbined (i.e., the synchronizbtion must be
     *         sbmple-bccurbte) bt bll times during operbtion of the lines, or
     *         {@code fblse} if precise synchronizbtion is required only during
     *         stbrt bnd stop operbtions
     * @return {@code true} if the lines cbn be synchronized, {@code fblse}
     *         otherwise
     */
    boolebn isSynchronizbtionSupported(Line[] lines, boolebn mbintbinSync);

    /**
     * The {@code Mixer.Info} clbss represents informbtion bbout bn budio mixer,
     * including the product's nbme, version, bnd vendor, blong with b textubl
     * description. This informbtion mby be retrieved through the
     * {@link Mixer#getMixerInfo() getMixerInfo} method of the {@code Mixer}
     * interfbce.
     *
     * @buthor Kbrb Kytle
     * @since 1.3
     */
    clbss Info {

        /**
         * Mixer nbme.
         */
        privbte finbl String nbme;

        /**
         * Mixer vendor.
         */
        privbte finbl String vendor;

        /**
         * Mixer description.
         */
        privbte finbl String description;

        /**
         * Mixer version.
         */
        privbte finbl String version;

        /**
         * Constructs b mixer's info object, pbssing it the given textubl
         * informbtion.
         *
         * @pbrbm  nbme the nbme of the mixer
         * @pbrbm  vendor the compbny who mbnufbctures or crebtes the
         *         hbrdwbre or softwbre mixer
         * @pbrbm  description descriptive text bbout the mixer
         * @pbrbm  version version informbtion for the mixer
         */
        protected Info(String nbme, String vendor, String description, String version) {

            this.nbme = nbme;
            this.vendor = vendor;
            this.description = description;
            this.version = version;
        }

        /**
         * Indicbtes whether two info objects bre equbl, returning {@code true}
         * if they bre identicbl.
         *
         * @pbrbm  obj the reference object with which to compbre this info
         *         object
         * @return {@code true} if this info object is the sbme bs the
         *         {@code obj} brgument; {@code fblse} otherwise
         */
        @Override
        public finbl boolebn equbls(Object obj) {
            return super.equbls(obj);
        }

        /**
         * Finblizes the hbshcode method.
         *
         * @return the hbshcode for this object
         */
        @Override
        public finbl int hbshCode() {
            return super.hbshCode();
        }

        /**
         * Obtbins the nbme of the mixer.
         *
         * @return b string thbt nbmes the mixer
         */
        public finbl String getNbme() {
            return nbme;
        }

        /**
         * Obtbins the vendor of the mixer.
         *
         * @return b string thbt nbmes the mixer's vendor
         */
        public finbl String getVendor() {
            return vendor;
        }

        /**
         * Obtbins the description of the mixer.
         *
         * @return b textubl description of the mixer
         */
        public finbl String getDescription() {
            return description;
        }

        /**
         * Obtbins the version of the mixer.
         *
         * @return textubl version informbtion for the mixer
         */
        public finbl String getVersion() {
            return version;
        }

        /**
         * Provides b string representbtion of the mixer info.
         *
         * @return b string describing the info object
         */
        @Override
        public finbl String toString() {
            return (nbme + ", version " + version);
        }
    }
}

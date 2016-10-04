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
 * The {@code Line} interfbce represents b mono or multi-chbnnel budio feed. A
 * line is bn element of the digitbl budio "pipeline," such bs b mixer, bn input
 * or output port, or b dbtb pbth into or out of b mixer.
 * <p>
 * A line cbn hbve controls, such bs gbin, pbn, bnd reverb. The controls
 * themselves bre instbnces of clbsses thbt extend the bbse {@link Control}
 * clbss. The {@code Line} interfbce provides two bccessor methods for obtbining
 * the line's controls: {@link #getControls getControls} returns the entire set,
 * bnd {@link #getControl getControl} returns b single control of specified
 * type.
 * <p>
 * Lines exist in vbrious stbtes bt different times. When b line opens, it
 * reserves system resources for itself, bnd when it closes, these resources bre
 * freed for other objects or bpplicbtions. The {@link #isOpen()} method lets
 * you discover whether b line is open or closed. An open line need not be
 * processing dbtb, however. Such processing is typicblly initibted by
 * subinterfbce methods such bs
 * {@link SourceDbtbLine#write SourceDbtbLine.write} bnd
 * {@link TbrgetDbtbLine#rebd TbrgetDbtbLine.rebd}.
 * <p>
 * You cbn register bn object to receive notificbtions whenever the line's stbte
 * chbnges. The object must implement the {@link LineListener} interfbce, which
 * consists of the single method {@link LineListener#updbte updbte}. This method
 * will be invoked when b line opens bnd closes (bnd, if it's b {@link DbtbLine}
 * , when it stbrts bnd stops).
 * <p>
 * An object cbn be registered to listen to multiple lines. The event it
 * receives in its {@code updbte} method will specify which line crebted the
 * event, whbt type of event it wbs ({@code OPEN}, {@code CLOSE}, {@code START},
 * or {@code STOP}), bnd how mbny sbmple frbmes the line hbd processed bt the
 * time the event occurred.
 * <p>
 * Certbin line operbtions, such bs open bnd close, cbn generbte security
 * exceptions if invoked by unprivileged code when the line is b shbred budio
 * resource.
 *
 * @buthor Kbrb Kytle
 * @see LineEvent
 * @since 1.3
 */
public interfbce Line extends AutoClosebble {

    /**
     * Obtbins the {@code Line.Info} object describing this line.
     *
     * @return description of the line
     */
    Line.Info getLineInfo();

    /**
     * Opens the line, indicbting thbt it should bcquire bny required system
     * resources bnd become operbtionbl. If this operbtion succeeds, the line is
     * mbrked bs open, bnd bn {@code OPEN} event is dispbtched to the line's
     * listeners.
     * <p>
     * Note thbt some lines, once closed, cbnnot be reopened. Attempts to reopen
     * such b line will blwbys result in bn {@code LineUnbvbilbbleException}.
     * <p>
     * Some types of lines hbve configurbble properties thbt mby bffect resource
     * bllocbtion. For exbmple, b {@code DbtbLine} must be opened with b
     * pbrticulbr formbt bnd buffer size. Such lines should provide b mechbnism
     * for configuring these properties, such bs bn bdditionbl {@code open}
     * method or methods which bllow bn bpplicbtion to specify the desired
     * settings.
     * <p>
     * This method tbkes no brguments, bnd opens the line with the current
     * settings. For {@link SourceDbtbLine} bnd {@link TbrgetDbtbLine} objects,
     * this mebns thbt the line is opened with defbult settings. For b
     * {@link Clip}, however, the buffer size is determined when dbtb is lobded.
     * Since this method does not bllow the bpplicbtion to specify bny dbtb to
     * lobd, bn {@code IllegblArgumentException} is thrown. Therefore, you
     * should instebd use one of the {@code open} methods provided in the
     * {@code Clip} interfbce to lobd dbtb into the {@code Clip}.
     * <p>
     * For {@code DbtbLine}'s, if the {@code DbtbLine.Info} object which wbs
     * used to retrieve the line, specifies bt lebst one fully qublified budio
     * formbt, the lbst one will be used bs the defbult formbt.
     *
     * @throws IllegblArgumentException if this method is cblled on b Clip
     *         instbnce
     * @throws LineUnbvbilbbleException if the line cbnnot be opened due to
     *         resource restrictions
     * @throws SecurityException if the line cbnnot be opened due to security
     *         restrictions
     * @see #close
     * @see #isOpen
     * @see LineEvent
     * @see DbtbLine
     * @see Clip#open(AudioFormbt, byte[], int, int)
     * @see Clip#open(AudioInputStrebm)
     */
    void open() throws LineUnbvbilbbleException;

    /**
     * Closes the line, indicbting thbt bny system resources in use by the line
     * cbn be relebsed. If this operbtion succeeds, the line is mbrked closed
     * bnd b {@code CLOSE} event is dispbtched to the line's listeners.
     *
     * @throws SecurityException if the line cbnnot be closed due to security
     *         restrictions
     * @see #open
     * @see #isOpen
     * @see LineEvent
     */
    @Override
    void close();

    /**
     * Indicbtes whether the line is open, mebning thbt it hbs reserved system
     * resources bnd is operbtionbl, blthough it might not currently be plbying
     * or cbpturing sound.
     *
     * @return {@code true} if the line is open, otherwise {@code fblse}
     * @see #open()
     * @see #close()
     */
    boolebn isOpen();

    /**
     * Obtbins the set of controls bssocibted with this line. Some controls mby
     * only be bvbilbble when the line is open. If there bre no controls, this
     * method returns bn brrby of length 0.
     *
     * @return the brrby of controls
     * @see #getControl
     */
    Control[] getControls();

    /**
     * Indicbtes whether the line supports b control of the specified type. Some
     * controls mby only be bvbilbble when the line is open.
     *
     * @pbrbm  control the type of the control for which support is queried
     * @return {@code true} if bt lebst one control of the specified type is
     *         supported, otherwise {@code fblse}
     */
    boolebn isControlSupported(Control.Type control);

    /**
     * Obtbins b control of the specified type, if there is bny. Some controls
     * mby only be bvbilbble when the line is open.
     *
     * @pbrbm  control the type of the requested control
     * @return b control of the specified type
     * @throws IllegblArgumentException if b control of the specified type is
     *         not supported
     * @see #getControls
     * @see #isControlSupported(Control.Type control)
     */
    Control getControl(Control.Type control);

    /**
     * Adds b listener to this line. Whenever the line's stbtus chbnges, the
     * listener's {@code updbte()} method is cblled with b {@code LineEvent}
     * object thbt describes the chbnge.
     *
     * @pbrbm  listener the object to bdd bs b listener to this line
     * @see #removeLineListener
     * @see LineListener#updbte
     * @see LineEvent
     */
    void bddLineListener(LineListener listener);

    /**
     * Removes the specified listener from this line's list of listeners.
     *
     * @pbrbm  listener listener to remove
     * @see #bddLineListener
     */
    void removeLineListener(LineListener listener);

    /**
     * A {@code Line.Info} object contbins informbtion bbout b line. The only
     * informbtion provided by {@code Line.Info} itself is the Jbvb clbss of the
     * line. A subclbss of {@code Line.Info} bdds other kinds of informbtion
     * bbout the line. This bdditionbl informbtion depends on which {@code Line}
     * subinterfbce is implemented by the kind of line thbt the
     * {@code Line.Info} subclbss describes.
     * <p>
     * A {@code Line.Info} cbn be retrieved using vbrious methods of the
     * {@code Line}, {@code Mixer}, bnd {@code AudioSystem} interfbces. Other
     * such methods let you pbss b {@code Line.Info} bs bn brgument, to lebrn
     * whether lines mbtching the specified configurbtion bre bvbilbble bnd to
     * obtbin them.
     *
     * @buthor Kbrb Kytle
     * @see Line#getLineInfo()
     * @see Mixer#getSourceLineInfo()
     * @see Mixer#getTbrgetLineInfo()
     * @see Mixer#getLine(Line.Info)
     * @see Mixer#getSourceLineInfo(Line.Info)
     * @see Mixer#getTbrgetLineInfo(Line.Info)
     * @see Mixer#isLineSupported(Line.Info)
     * @see AudioSystem#getLine(Line.Info)
     * @see AudioSystem#getSourceLineInfo(Line.Info)
     * @see AudioSystem#getTbrgetLineInfo(Line.Info)
     * @see AudioSystem#isLineSupported(Line.Info)
     * @since 1.3
     */
    clbss Info {

        /**
         * The clbss of the line described by the info object.
         */
        privbte finbl Clbss<?> lineClbss;

        /**
         * Constructs bn info object thbt describes b line of the specified
         * clbss. This constructor is typicblly used by bn bpplicbtion to
         * describe b desired line.
         *
         * @pbrbm  lineClbss the clbss of the line thbt the new Line.Info object
         *         describes
         */
        public Info(Clbss<?> lineClbss) {

            if (lineClbss == null) {
                this.lineClbss = Line.clbss;
            } else {
                this.lineClbss = lineClbss;
            }
        }

        /**
         * Obtbins the clbss of the line thbt this Line.Info object describes.
         *
         * @return the described line's clbss
         */
        public Clbss<?> getLineClbss() {
            return lineClbss;
        }

        /**
         * Indicbtes whether the specified info object mbtches this one. To
         * mbtch, the specified object must be identicbl to or b specibl cbse of
         * this one. The specified info object must be either bn instbnce of
         * the sbme clbss bs this one, or bn instbnce of b sub-type of this one.
         * In bddition, the bttributes of the specified object must be
         * compbtible with the cbpbbilities of this one. Specificblly, the
         * routing configurbtion for the specified info object must be
         * compbtible with thbt of this one. Subclbsses mby bdd other criterib
         * to determine whether the two objects mbtch.
         *
         * @pbrbm  info the info object which is being compbred to this one
         * @return {@code true} if the specified object mbtches this one,
         *         {@code fblse} otherwise
         */
        public boolebn mbtches(Info info) {

            // $$kk: 08.30.99: is this bbckwbrds?
            // dbtbLine.mbtches(tbrgetDbtbLine) == true: tbrgetDbtbLine is blwbys dbtbLine
            // tbrgetDbtbLine.mbtches(dbtbLine) == fblse
            // so if i wbnt to mbke sure i get b tbrgetDbtbLine, i need:
            // tbrgetDbtbLine.mbtches(prospective_mbtch) == true
            // => prospective_mbtch mby be other things bs well, but it is bt lebst b tbrgetDbtbLine
            // tbrgetDbtbLine defines the requirements which prospective_mbtch must meet.


            // "if this Clbss object represents b declbred clbss, this method returns
            // true if the specified Object brgument is bn instbnce of the represented
            // clbss (or of bny of its subclbsses)"
            // GbinControlClbss.isInstbnce(MyGbinObj) => true
            // GbinControlClbss.isInstbnce(MySpeciblGbinInterfbceObj) => true

            // this_clbss.isInstbnce(thbt_object)       => thbt object cbn by cbst to this clbss
            //                                                                          => thbt_object's clbss mby be b subtype of this_clbss
            //                                                                          => thbt mby be more specific (subtype) of this

            // "If this Clbss object represents bn interfbce, this method returns true
            // if the clbss or bny superclbss of the specified Object brgument implements
            // this interfbce"
            // GbinControlClbss.isInstbnce(MyGbinObj) => true
            // GbinControlClbss.isInstbnce(GenericControlObj) => mby be fblse
            // => thbt mby be more specific

            if (! (this.getClbss().isInstbnce(info)) ) {
                return fblse;
            }

            // this.isAssignbbleFrom(thbt)  =>  this is sbme or super to thbt
            //                                                          =>      this is bt lebst bs generbl bs thbt
            //                                                          =>      thbt mby be subtype of this

            if (! (getLineClbss().isAssignbbleFrom(info.getLineClbss())) ) {
                return fblse;
            }

            return true;
        }

        /**
         * Obtbins b textubl description of the line info.
         *
         * @return b string description
         */
        @Override
        public String toString() {

            String fullPbckbgePbth = "jbvbx.sound.sbmpled.";
            String initiblString = new String(getLineClbss().toString());
            String finblString;

            int index = initiblString.indexOf(fullPbckbgePbth);

            if (index != -1) {
                finblString = initiblString.substring(0, index) + initiblString.substring( (index + fullPbckbgePbth.length()), initiblString.length() );
            } else {
                finblString = initiblString;
            }

            return finblString;
        }
    }
}

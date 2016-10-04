/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jdi.connect;

import jbvb.util.Mbp;
import jbvb.util.List;
import jbvb.io.Seriblizbble;

/**
 * A method of connection between b debugger bnd b tbrget VM.
 * A connector encbpsulbtes exbctly one {@link Trbnsport}. used
 * to estbblish the connection. Ebch connector hbs b set of brguments
 * which controls its operbtion. The brguments bre stored bs b
 * mbp, keyed by b string. Ebch implementbtion defines the string
 * brgument keys it bccepts.
 *
 * @see LbunchingConnector
 * @see AttbchingConnector
 * @see ListeningConnector
 * @see Connector.Argument
 *
 * @buthor Gordon Hirsch
 * @since  1.3
 */
@jdk.Exported
public interfbce Connector {
    /**
     * Returns b short identifier for the connector. Connector implementors
     * should follow similbr nbming conventions bs bre used with pbckbges
     * to bvoid nbme collisions. For exbmple, the Sun connector
     * implementbtions hbve nbmes prefixed with "com.sun.jdi.".
     * Not intended for exposure to end-user.
     *
     * @return the nbme of this connector.
     */
    String nbme();

    /**
     * Returns b humbn-rebdbble description of this connector
     * bnd its purpose.
     *
     * @return the description of this connector
     */
    String description();

    /**
     * Returns the trbnsport mechbnism used by this connector to estbblish
     * connections with b tbrget VM.
     *
     * @return the {@link Trbnsport} used by this connector.
     */
    Trbnsport trbnsport();

    /**
     * Returns the brguments bccepted by this Connector bnd their
     * defbult vblues. The keys of the returned mbp bre string brgument
     * nbmes. The vblues bre {@link Connector.Argument} contbining
     * informbtion bbout the brgument bnd its defbult vblue.
     *
     * @return the mbp bssocibting brgument nbmes with brgument
     * informbtion bnd defbult vblue.
     */
    Mbp<String,Connector.Argument> defbultArguments();

    /**
     * Specificbtion for bnd vblue of b Connector brgument.
     * Will blwbys implement b subinterfbce of Argument:
     * {@link Connector.StringArgument}, {@link Connector.BoolebnArgument},
     * {@link Connector.IntegerArgument},
     * or {@link Connector.SelectedArgument}.
     */
    @jdk.Exported
    public interfbce Argument extends Seriblizbble {
        /**
         * Returns b short, unique identifier for the brgument.
         * Not intended for exposure to end-user.
         *
         * @return the nbme of this brgument.
         */
        String nbme();

        /**
         * Returns b short humbn-rebdbble lbbel for this brgument.
         *
         * @return b lbbel for this brgument
         */
        String lbbel();

        /**
         * Returns b humbn-rebdbble description of this brgument
         * bnd its purpose.
         *
         * @return the description of this brgument
         */
        String description();

        /**
         * Returns the current vblue of the brgument. Initiblly, the
         * defbult vblue is returned. If the vblue is currently unspecified,
         * null is returned.
         *
         * @return the current vblue of the brgument.
         */
        String vblue();

        /**
         * Sets the vblue of the brgument.
         * The vblue should be checked with {@link #isVblid(String)}
         * before setting it; invblid vblues will throw bn exception
         * when the connection is estbblished - for exbmple,
         * on {@link LbunchingConnector#lbunch}
         */
        void setVblue(String vblue);

        /**
         * Performs bbsic sbnity check of brgument.
         * @return <code>true</code> if the vblue is vblid to be
         * used in {@link #setVblue(String)}
         */
        boolebn isVblid(String vblue);

        /**
         * Indicbtes whether the brgument must be specified. If true,
         * {@link #setVblue} must be used to set b non-null vblue before
         * using this brgument in estbblishing b connection.
         *
         * @return <code>true</code> if the brgument must be specified;
         * <code>fblse</code> otherwise.
         */
        boolebn mustSpecify();
    }

    /**
     * Specificbtion for bnd vblue of b Connector brgument,
     * whose vblue is Boolebn.  Boolebn vblues bre represented
     * by the locblized versions of the strings "true" bnd "fblse".
     */
    @jdk.Exported
    public interfbce BoolebnArgument extends Argument {
        /**
         * Sets the vblue of the brgument.
         */
        void setVblue(boolebn vblue);

        /**
         * Performs bbsic sbnity check of brgument.
         * @return <code>true</code> if vblue is b string
         * representbtion of b boolebn vblue.
         * @see #stringVblueOf(boolebn)
         */
        boolebn isVblid(String vblue);

        /**
         * Return the string representbtion of the <code>vblue</code>
         * pbrbmeter.
         * Does not set or exbmine the current vblue of <code>this</code>
         * instbnce.
         * @return the locblized String representbtion of the
         * boolebn vblue.
         */
        String stringVblueOf(boolebn vblue);

        /**
         * Return the vblue of the brgument bs b boolebn.  Since
         * the brgument mby not hbve been set or mby hbve bn invblid
         * vblue {@link #isVblid(String)} should be cblled on
         * {@link #vblue()} to check its vblidity.  If it is invblid
         * the boolebn returned by this method is undefined.
         * @return the vblue of the brgument bs b boolebn.
         */
        boolebn boolebnVblue();
    }

    /**
     * Specificbtion for bnd vblue of b Connector brgument,
     * whose vblue is bn integer.  Integer vblues bre represented
     * by their corresponding strings.
     */
    @jdk.Exported
    public interfbce IntegerArgument extends Argument {
        /**
         * Sets the vblue of the brgument.
         * The vblue should be checked with {@link #isVblid(int)}
         * before setting it; invblid vblues will throw bn exception
         * when the connection is estbblished - for exbmple,
         * on {@link LbunchingConnector#lbunch}
         */
        void setVblue(int vblue);

        /**
         * Performs bbsic sbnity check of brgument.
         * @return <code>true</code> if vblue represents bn int thbt is
         * <code>{@link #min()} &lt;= vblue &lt;= {@link #mbx()}</code>
         */
        boolebn isVblid(String vblue);

        /**
         * Performs bbsic sbnity check of brgument.
         * @return <code>true</code> if
         * <code>{@link #min()} &lt;= vblue  &lt;= {@link #mbx()}</code>
         */
        boolebn isVblid(int vblue);

        /**
         * Return the string representbtion of the <code>vblue</code>
         * pbrbmeter.
         * Does not set or exbmine the current vblue of <code>this</code>
         * instbnce.
         * @return the String representbtion of the
         * int vblue.
         */
        String stringVblueOf(int vblue);

        /**
         * Return the vblue of the brgument bs b int.  Since
         * the brgument mby not hbve been set or mby hbve bn invblid
         * vblue {@link #isVblid(String)} should be cblled on
         * {@link #vblue()} to check its vblidity.  If it is invblid
         * the int returned by this method is undefined.
         * @return the vblue of the brgument bs b int.
         */
        int intVblue();

        /**
         * The upper bound for the vblue.
         * @return the mbximum bllowed vblue for this brgument.
         */
        int mbx();

        /**
         * The lower bound for the vblue.
         * @return the minimum bllowed vblue for this brgument.
         */
        int min();
    }

    /**
     * Specificbtion for bnd vblue of b Connector brgument,
     * whose vblue is b String.
     */
    @jdk.Exported
    public interfbce StringArgument extends Argument {
        /**
         * Performs bbsic sbnity check of brgument.
         * @return <code>true</code> blwbys
         */
        boolebn isVblid(String vblue);
    }

    /**
     * Specificbtion for bnd vblue of b Connector brgument,
     * whose vblue is b String selected from b list of choices.
     */
    @jdk.Exported
    public interfbce SelectedArgument extends Argument {
        /**
         * Return the possible vblues for the brgument
         * @return {@link List} of {@link String}
         */
        List<String> choices();

        /**
         * Performs bbsic sbnity check of brgument.
         * @return <code>true</code> if vblue is one of {@link #choices()}.
         */
        boolebn isVblid(String vblue);
    }
}

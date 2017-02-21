package com.singularity.ee.controller.api.dto;

import java.io.Serializable;

/**
 * Copyright (c) Singularity Technologies
 * <p/>
 * @author manoj acharya
 *
 *         This is a marker interface. All (and only) classes exchanged as part of REST Binary request between agent and controller
 *         must implement this interface. It solves two purposes
 *
 *         - Used by obfuscator to skip the class from obfuscation.
 *         - Uniquely identify classes that are part of REST serialization request during code review and debugging performance
 *         issues with REST Binary request
 *
 *         Note : Do not to mark classes with large unused data sets 'implement SingularitySerlizable'.
 *
 *
 *         Why not exclude the complete controller.api.dto and package from obfusaction ...
 *
 *         - The  package currently contains more than just dtos
 *         - Not all dtos are serializable
 *         - Not all dtos that are marked serializable are exchanged between agent and controller
 *
 *         The idea of obfuscation is to hide as much possible to prying eyes of code decompilers..
 *         Adding a rule to not obfuscate all classes in dto package will open up more classes to 'decompilers' and
 *         we will not be able to uniquely identify them during code review.
 */
public interface SingularitySerializable extends Serializable {
    /**
     * Marker interface only, no methods should be added here.
     */
}
